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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
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
        System.out.println("asked for my invits");
        List<Invit> list = invitDao.findByOwner(getCurrent(context).getId());
        for (Invit invit : list){
            invit.setNameEvent(eventDao.getName(invit.getEvent()));
        }
        return list;
    }

    private User getCurrent(SecurityContext context){
        User currentUser = (User) context.getUserPrincipal();
        logger.debug("Current User :"+ currentUser.toString());
        if (User.isAnonymous(currentUser)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"").entity("Ressouce requires login.").build());
        }
        return currentUser;
    }
}
