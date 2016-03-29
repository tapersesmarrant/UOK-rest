package fr.iutinfo.skeleton.api;

import com.google.common.base.Strings;
import fr.iutinfo.skeleton.res.dao.UserDao;
import fr.iutinfo.skeleton.res.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = BDDFactory.getDbi().onDemand(UserDao.class);

    @POST
    public Response createUser(User user) {
        user.setRole(User.Roles.user);
        if (Strings.nullToEmpty(user.getTelNumber()).isEmpty()){
            return Response.status(Status.BAD_REQUEST).entity("field telNumer is required.").build();
        }
        if (dao.findByNumber(user.getTelNumber()) != null){
            return Response.status(Status.CONFLICT).entity(dao.findByNumber(user.getTelNumber()).getAlias()).build();
        }
        if (Strings.nullToEmpty(user.getPassword()).isEmpty()){
            return Response.status(Status.BAD_REQUEST).entity("field password is required.").build();
        }
        if (Strings.nullToEmpty(user.getEmail()).isEmpty()){
            return Response.status(Status.BAD_REQUEST).entity("field email is required.").build();
        }
        user.resetPasswordHash();
        dao.insert(user);
        return Response.ok(dao.findByNumber(user.getTelNumber())).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@Context SecurityContext context, @PathParam("id")int id,
                               User user) {
        User currrent = PerosnalDBResource.getCurrent(context);
        if (currrent.getId() != id){
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity("Not your own id").build());
        }
        currrent.updateFrom(user);
        dao.updateUser(currrent);
        return Response.status(200).entity(currrent).build();
    }

    @GET
    @Path("/byNumber/{number}")
    public User getUserByNumber(@Context SecurityContext context, @PathParam("number") String number) {

        User currentUser = (User) context.getUserPrincipal();
        logger.debug("Current User :"+ currentUser.toString());
        User user = dao.findByNumber(number);
        if (user == null){
            throw new WebApplicationException(404);
        }
        if (User.isAnonymous(currentUser) || user.getId() != currentUser.getId()) {
            user.anonymise();
        }
        return user;
    }

    @GET
    @Path("/byEmail/{email}")
    public User getUserByEmail(@Context SecurityContext context, @PathParam("email") String email) {

        User currentUser = (User) context.getUserPrincipal();
        User user = dao.findByNumber(email);
        if (user == null){
            throw new WebApplicationException(404);
        }
        if (User.isAnonymous(currentUser) || user.getId() != currentUser.getId()) {
            user.anonymise();
        }
        return user;
    }

    @GET
    public List<User> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
        List<User> alls = dao.all();
        for (User all : alls) {
            all.anonymise();
        }
        return alls;
    }

}
