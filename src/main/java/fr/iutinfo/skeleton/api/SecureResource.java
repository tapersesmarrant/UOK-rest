package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/secure")
public class SecureResource {
    final static Logger logger = LoggerFactory.getLogger(SecureResource.class);

    @GET
    @Path("/forall")
    public UserDTO secureForAll(@Context SecurityContext context) {
        return (UserDTO) context.getUserPrincipal();
    }

    @GET
    @Path("/onlylogged")
    public UserDTO secureForLoggedUsers(@Context SecurityContext context) {
        UserDTO currentUser = (UserDTO) context.getUserPrincipal();
        logger.debug("Current UserDTO :"+ currentUser.toString());
        if (UserDTO.isAnonymous(currentUser)) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"").entity("Ressouce requires login.").build());
        }
        return currentUser;
    }

    @GET
    @Path("/byannotation")
    @RolesAllowed({"user"})
    public UserDTO secureByAnnotation(@Context SecurityContext context) {
        return (UserDTO) context.getUserPrincipal();
    }

}
