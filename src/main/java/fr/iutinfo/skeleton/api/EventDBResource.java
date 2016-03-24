package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.Event;
import fr.iutinfo.skeleton.res.EventDao;
import fr.iutinfo.skeleton.res.User;
import fr.iutinfo.skeleton.res.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/eventdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventDBResource {
	private static EventDao dao = BDDFactory.getDbi().onDemand(EventDao.class);
    final static Logger logger = LoggerFactory.getLogger(EventDBResource.class);


    public EventDBResource() {
	}
	
	@POST
	public Event createUser(Event user) {
        int id = dao.insert(user);
        user.setId(id);
		return user;
	}


    @GET
    @Path("/{id}")
    public Event getUserById(@PathParam("id") int id){
        Event event = dao.findById(id);
        if (event == null) {
            throw new WebApplicationException(404);
        }
        return event;
    }

    @GET
    @Path("/owner/{id}")
    public List<Event> getUserByOwner(@PathParam("id") int id){
        return dao.all(id);
    }

	@GET
	public List<Event> getAllUsers() {
		return dao.all();
	}

}
