package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private static Map<Integer, UserDTO> users = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(UserResource.class);

    @POST
    public UserDTO createUser(UserDTO user) {
        int id = users.size();
        user.setId(id + 1);
        users.put(user.getId(), user);
        return user;
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (users.containsKey(id)) {
            return Response.accepted().status(Status.ACCEPTED).build();
        }
        return Response.accepted().status(Status.NOT_FOUND).build();
    }

    protected UserDTO find(String name) {
        for (UserDTO user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    protected UserDTO find(int id) {
        return users.get(id);
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id,
                               UserDTO user) {
        UserDTO oldUser = find(id);
        logger.info("Should update user with id: " + id + " (" + oldUser + ") to " + user);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        oldUser.setName(user.getName());
        return Response.status(200).entity(oldUser).build();
    }

    @GET
    @Path("/{name}")
    public UserDTO getUser(@PathParam("name") String name) {
        UserDTO out = find(name);
        if (out == null) {
            throw new WebApplicationException(404);
        }
        return out;
    }

    @GET
    public List<UserDTO> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
        return new ArrayList<>(users.values());
    }

}
