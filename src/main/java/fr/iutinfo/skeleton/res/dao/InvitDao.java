package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.utils.binders.BindEvent;
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

            "event INTEGER,"+
            "user INTEGER, " +
            "timestamp date, " +
            "isSecondaryList  boolean, " +
            "isFired boolean, " +
            "CONSTRAINT pk_invit PRIMARY KEY (user,event)" )
    void createUserTable();

    @SqlUpdate("insert into invit (event, user,timestamp ,isSecondaryList,isFired)"+
            "values (:event, :user,:timestamp,:isSecondaryList,:isfired)")
    @GetGeneratedKeys
    int insert(@BindEvent() Invit invit);

    @SqlUpdate  ("DELETE from invit where event=:event AND user:=user")
    void deleteInvit(@Bind("id") int id);


    @SqlUpdate("drop table if exists invit")
    void dropUserTable();









}
