package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.utils.binders.BindEvent;
import fr.iutinfo.skeleton.utils.binders.BindInvit;
import fr.iutinfo.skeleton.utils.binders.BindUser;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

/**
 * Created by Ouvryl on 25/03/16.
 */
public interface InvitDao {
    @SqlUpdate("create table IF NOT EXISTS invit (" +

            "event INTEGER REFERENCES event(id),"+
            "user INTEGER  REFERENCES users(id), " +
            "timestamp date, " +
            "isSecondaryList  boolean, " +
            "isFired boolean, " +
            "isOk boolean, " +
            "CONSTRAINT pk_invit PRIMARY KEY (user,event))" )
    void createUserTable();

    @SqlUpdate("insert into invit (event, user,timestamp ,isSecondaryList,isFired, isOk)"+
            "values (:event, :user,:timestamp,:isSecondaryList,:isfired, :isOk)")
    @GetGeneratedKeys
    int insert(@BindInvit() Invit invit);

    @SqlUpdate  ("DELETE from invit where event=:event AND user:=user")
    void deleteInvit(@Bind("event") int idEvent, @Bind("user") int idUser);


    @SqlUpdate ("UPDATE invit SET isFired=:isfired, isOk=:isOk where user=:user and evnet=:event")
    void update(@BindInvit() Invit invit);


    @SqlUpdate("drop table if exists invit")
    void dropInvitTable();

    @SqlQuery("select * from invit where event = :event")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Invit> findByEvent(@Bind("event") int idEvent);

    @SqlQuery("select * from invit where user=:user")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Invit> findByOwner(@Bind("user") int idUser);

    @SqlQuery("select * from invit where user=:user and event=:event")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Invit getInvitBy(@Bind("event") int event, @Bind("user") int user);

    void close();

    @SqlQuery("SELECT * from invit")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Invit> all();
}
