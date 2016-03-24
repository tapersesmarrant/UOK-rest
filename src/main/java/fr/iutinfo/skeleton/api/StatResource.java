package fr.iutinfo.skeleton.api;


import fr.iutinfo.skeleton.res.StatDao;
import fr.iutinfo.skeleton.res.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by nicbe on 24/03/2016.
 */
@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatResource {
    final static Logger logger = LoggerFactory.getLogger(StatResource.class);
    final static private StatDao dao = BDDFactory.getDbi().onDemand(StatDao.class);


    @GET
    public Stats getStats() {
        Stats s = new Stats(dao);
        logger.info("returning {} for stats",s);
        return s;
    }

}
