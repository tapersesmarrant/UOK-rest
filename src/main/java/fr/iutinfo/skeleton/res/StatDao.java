package fr.iutinfo.skeleton.res;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * Created by nicbe on 24/03/2016.
 */
public interface StatDao {

    @SqlQuery("SELECT COUNT(*) FROM users")
    int getNmbUsers();
    @SqlQuery("SELECT COUNT(*) FROM event")
    int getNmbEvents();
}
