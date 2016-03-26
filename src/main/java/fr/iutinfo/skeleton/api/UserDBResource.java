package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.res.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDBResource {
	private static UserDao dao = BDDFactory.getDbi().onDemand(UserDao.class);
    final static Logger logger = LoggerFactory.getLogger(UserDBResource.class);


    public UserDBResource() {
	}
	
	@POST
	public User createUser(User user) {
        user.resetPasswordHash();
        int id = dao.insert(user);
        user.setId(id);
		return user;
	}

	@GET
	@Path("/byEmail/{email}")
	public User getUser(@PathParam("email") String email) {
		User user = dao.findByEmail(email);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	@Path("/byNum/{number}")
	public User getUserByNumber(@PathParam("number") String number) {
		User user = dao.findByNumber(number);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") int id){
        User user = dao.findById(id);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        return user;
    }


    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id,
                               User user) {
        User oldUser = dao.findById(id);
        logger.info("Should update user with id: " + id + " (" + oldUser + ") to " + user);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        oldUser.updateFrom(user);
        dao.updateUser(oldUser);

        return Response.status(200).entity(oldUser).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User user = dao.findById(id);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        dao.deleteUser(id);
        return Response.status(200).entity(user).build();
    }

	@GET
	public List<User> getAllUsers() {
		return dao.all();
	}

}
