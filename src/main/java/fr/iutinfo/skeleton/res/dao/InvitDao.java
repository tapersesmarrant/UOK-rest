package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.utils.binders.BindUser;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

/**
 * Created by Ouvryl on 25/03/16.
 */
public interface InvitDao {
    @SqlUpdate("create table IF NOT EXISTS invit (" +
            "id integer primary key auto_increment, " +
            "envent INTEGER,"+
            "user INTEGER, " +
            "timestamp date, " +
            "isSecondaryList  boolean, " +
            "isFired boolean)" );
    void createUserTable();

    @SqlUpdate("insert into invit (envent, user,timestamp ,isSecondaryList,isFired")+
            "values (:owner, :location,:date,:name,:limitTime,:isLock,:isRush,:isTime,:timeBeforeRush,:cost,:isDone,:isCanceled)")




}
