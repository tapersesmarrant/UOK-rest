package fr.iutinfo.skeleton.api;

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
import java.util.List;


/**
 * Created by nicbe on 25/03/2016.
 */
@Path("/me")
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
        event.setOwner(getCurrent(context).getId());
        eventDao.insert(event);
        for (Invit invit : event.getInvit()){
            if (invit.getUser() <= 0){
                invitDao.insert(invit);
            } else {
                User user = invit.getUserObject();
                User telUser = userDao.findByNumber(user.getTelNumber());
                if (telUser != null){
                    invit.setUser(telUser.getId());
                    invitDao.insert(invit);
                } else {
                    userDao.insert(user);
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
        boolean haveToFilter = event.getOwner() != user.getId();
        event.setInvit(invitDao.findByEvent(event.getId()));
        boolean hasFoundUser = false;

        for (Invit invit :  event.getInvit()){
            if (!hasFoundUser){
                hasFoundUser = user.getId() == invit.getUser();
                invit.setFired(true);
                invitDao.insert(invit);
            }
            if (user.getId() == invit.getUser()){
                hasFoundUser = true;
            } else if (haveToFilter){
                invit.setUserObject(userDao.findById(invit.getUser()).minAnonymise());
            } else {
                invit.setUserObject(userDao.findById(invit.getUser()).anonymise());
            }

        }

        if (!hasFoundUser && !haveToFilter){
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Vous n'êtes pas dans l'evenemnt").build());
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
