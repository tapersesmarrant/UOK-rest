package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.dao.InvitDao;
import fr.iutinfo.skeleton.res.dao.UserDao;
import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


/**
 * Created by nicbe on 25/03/2016.
 */
@Path("/admin")
@RolesAllowed({"admin"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
    final static Logger logger = LoggerFactory.getLogger(AdminResource.class);

    private static UserDao userDao = BDDFactory.getDbi().onDemand(UserDao.class);
    private static EventDao eventDao = BDDFactory.getDbi().onDemand(EventDao.class);
    private static InvitDao invitDao = BDDFactory.getDbi().onDemand(InvitDao.class);


    @GET
    public Response secureForLoggedUsers(@Context SecurityContext context) {
        User currentUser = getCurrent(context);
        return Response.ok("{ \"id\":"+currentUser.getId() + ", \"name\":\""+currentUser.getName()+"\"}", MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/user")
    public List<User> getALlUsers(@Context SecurityContext context) {
        return userDao.all();
    }

    @GET
    @Path("/user/{id}")
    public User getAUser(@Context SecurityContext context, @PathParam("id") int id) {
        return userDao.findById(id);
    }


    @GET
    @Path("/event")
    public List<Event> getAllEvent(@Context SecurityContext context) {
        List<Event> ls = eventDao.all();

        for (Event l : ls) {
            l.setInvit(invitDao.findByEvent(l.getId()));
            for (Invit i : l.getInvit()){
                i.setUserObject(userDao.findById(i.getUser()));
            }
        }
        return ls;
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
