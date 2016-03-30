package fr.iutinfo.skeleton.api;

import com.google.common.base.Strings;
import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.dao.InvitDao;
import fr.iutinfo.skeleton.res.dao.UserDao;
import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by nicbe on 25/03/2016.
 */
@Path("/me")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerosnalDBResource {
    final static Logger logger = LoggerFactory.getLogger(PerosnalDBResource.class);

    private static UserDao userDao = BDDFactory.getDbi().onDemand(UserDao.class);
    private static EventDao eventDao = BDDFactory.getDbi().onDemand(EventDao.class);
    private static InvitDao invitDao = BDDFactory.getDbi().onDemand(InvitDao.class);


    @GET
    public Response secureForLoggedUsers(@Context SecurityContext context) {
        User currentUser = getCurrent(context);

        return Response.ok("{ \"id\":"+currentUser.getId() + ", \"name\":\""+currentUser.getName()+"\"}", MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/userFull")
    public User secureForLoggedUsersFull(@Context SecurityContext context) {
        User currentUser = getCurrent(context);
        return userDao.findById(currentUser.getId());
    }

    @GET
    @Path("/myEvents")
    public List<Event> getAllUserEvent(@Context SecurityContext context){
        User currentUser = getCurrent(context);
        return eventDao.all(currentUser.getId());
    }

    @GET
    @Path("/myEvents/full")
    public List<Event> getAllUserEventFull(@Context SecurityContext context){
        User user = getCurrent(context);
        List<Event> alls =eventDao.all(user.getId());
        for (Event event : alls) {
            boolean haveToFilter = event.getOwner() != user.getId();
            event.setInvit(invitDao.findByEvent(event.getId()));

            for (Invit invit :  event.getInvit()){
                if (user.getId() == invit.getUser()){
                    invit.setUserObject(user);
                } else if (haveToFilter){
                    invit.setUserObject(userDao.findById(invit.getUser()).minAnonymise());
                } else {
                    invit.setUserObject(userDao.findById(invit.getUser()).anonymise());
                }
            }
        }

        return alls;
    }

    @GET
    @Path("/myInvints")
    public List<Invit> getAllInvits(@Context SecurityContext context){
        List<Invit> list = invitDao.findByOwner(getCurrent(context).getId());
        for (Invit invit : list){
            invit.setNameEvent(eventDao.getName(invit.getEvent()));
        }
        return list;
    }

    @PUT
    @Path("/invit/{eventId}")
    public Invit putInvit(@Context SecurityContext context, @PathParam("eventId") int eventId, Invit invit){
        Invit dbinvint = invitDao.getInvitBy(eventId, getCurrent(context).getId());
        dbinvint.setFired(true);
        dbinvint.setOk(invit.isOk());
        invitDao.update(dbinvint);
        return dbinvint;
    }

    @POST
    @Path("/event")
    public Response postEvent(@Context SecurityContext context, Event event){
        logger.info("{}", Strings.repeat("-",30));
        logger.info("Event : {}", event.toString());
        logger.info("{}", Strings.repeat("-",30));
        logger.info("LIst Invit : {}", event.getInvit().toString());
        logger.info("{}", Strings.repeat("-",30));
        event.setOwner(getCurrent(context).getId());
        if (event.getDate() == null){
            event.setDate(new Date());
        }
        int id = eventDao.insert(event);
        for (Invit invit : event.getInvit()){
            invit.setEvent(id);

            User user = invit.getUserObject();
            if (user == null){
                for (int i = 0; i < 5; i++){
                    SecureRandom sr = new SecureRandom();
                    User u = new User();
                    invit.setUserObject(u);
                    String str = ""+sr.nextInt(100000000);
                    u.setTelNumber(""+ str);
                    u.setEmail("En attente de reponse pour " + str);
                    u.setName("En attente de reponse pour " + str);

                    invit.setUser(userDao.insert(u));
                    invitDao.insert(invit);
                }



            } else {
                User telUser = userDao.findByNumber(user.getTelNumber());
                if (telUser != null){
                    invit.setUser(telUser.getId());
                    invitDao.insert(invit);
                } else {
                    User newUser = new User();
                    newUser.setTelNumber(user.getTelNumber());
                    invit.setUser(userDao.insert(newUser));
                    invitDao.insert(invit);
                }
            }


        }
        return Response.accepted().build();
    }

    @GET
    @Path("/event/{id}")
    public Event getEvent(@Context SecurityContext context, @PathParam("id") int id){
        User user = getCurrent(context);
        Event event = eventDao.findById(id);
        boolean haveToFilter = false;
        event.setInvit(invitDao.findByEvent(event.getId()));
        boolean hasFoundUser = false;

        for (Invit invit :  event.getInvit()){
            if (!hasFoundUser){
                hasFoundUser = user.getId() == invit.getUser();
                invit.setFired(true);
                //invitDao.insert(invit);
            }
            if (user.getId() == invit.getUser()){
                hasFoundUser = true;
            } else if (haveToFilter){
                invit.setUserObject(userDao.findById(invit.getUser()).minAnonymise());
            } else {
                invit.setUserObject(userDao.findById(invit.getUser()).anonymise());
            }
        }

        return event;
    }

    static User getCurrent(SecurityContext context){
        User currentUser = (User) context.getUserPrincipal();
        logger.debug("Current User :"+ currentUser.toString());
        if (User.isAnonymous(currentUser)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"").entity("Ressouce requires login.").build());
        }
        return currentUser;
    }
}
