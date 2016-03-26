package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.dao.UserDao;
import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by nicbe on 25/03/2016.
 */
@Path("/me")
public class PerosnalDBResource {
    final static Logger logger = LoggerFactory.getLogger(PerosnalDBResource.class);

    private static UserDao userDao = BDDFactory.getDbi().onDemand(UserDao.class);
    private static EventDao eventDao = BDDFactory.getDbi().onDemand(EventDao.class);


    @GET
    public User secureForLoggedUsers(@Context SecurityContext context) {
        User currentUser = getCurrent(context);
        return userDao.findByIdLimited(currentUser.getId());
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

    private User getCurrent(SecurityContext context){
        User currentUser = (User) context.getUserPrincipal();
        logger.debug("Current User :"+ currentUser.toString());
        if (User.isAnonymous(currentUser)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"").entity("Ressouce requires login.").build());
        }
        return currentUser;
    }
}
