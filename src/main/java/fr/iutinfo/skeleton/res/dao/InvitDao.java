package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.InvitDTO;
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
            "CONSTRAINT pk_invit PRIMARY KEY (user,event))" )
    void createUserTable();

    @SqlUpdate("insert into invit (event, user,timestamp ,isSecondaryList,isFired)"+
            "values (:event, :user,:timestamp,:isSecondaryList,:isfired)")
    @GetGeneratedKeys
    int insert(@BindBean() InvitDTO invit);

    @SqlUpdate  ("DELETE from invit where event=:event AND user:=user")
    void deleteInvit(@Bind("event") int id, @Bind("user") int idUser);


    @SqlUpdate("drop table if exists invit")
    void dropInvitTable();

    @SqlQuery("select * from invit where event = :event")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<InvitDTO> findByEvent(@Bind("event") int idEvent);

    @SqlQuery("select * from invit where user=:user")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<InvitDTO> findByOwner(@Bind("user") int idUser);



    void close();





}
