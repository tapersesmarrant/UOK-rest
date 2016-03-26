package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.dao.UserDao;
import fr.iutinfo.skeleton.res.model.EventDTO;
import fr.iutinfo.skeleton.res.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public UserDTO secureForLoggedUsers(@Context SecurityContext context) {
        return isConnected(context);
    }

    @GET
    @Path("/myEvents")
    public List<EventDTO> getAllUserEvent(@Context SecurityContext context){
        UserDTO currentUser = isConnected(context);
        return eventDao.all(currentUser.getId());
    }

    private UserDTO isConnected(SecurityContext context){
        UserDTO currentUser = (UserDTO) context.getUserPrincipal();
        logger.debug("Current UserDTO :"+ currentUser.toString());
        if (UserDTO.isAnonymous(currentUser)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"").entity("Ressouce requires login.").build());
        }
        return currentUser;
    }
}
