package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.EventDTO;
import fr.iutinfo.skeleton.res.dao.EventDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
	public EventDTO createUser(EventDTO user) {
        int id = dao.insert(user);
        user.setId(id);
		return user;
	}


    @GET
    @Path("/{id}")
    public EventDTO getUserById(@PathParam("id") int id){
        EventDTO eventDTO = dao.findById(id);
        if (eventDTO == null) {
            throw new WebApplicationException(404);
        }
        return eventDTO;
    }

    @GET
    @Path("/owner/{id}")
    public List<EventDTO> getUserByOwner(@PathParam("id") int id){
        return dao.all(id);
    }

	@GET
	public List<EventDTO> getAllUsers() {
		return dao.all();
	}

}
