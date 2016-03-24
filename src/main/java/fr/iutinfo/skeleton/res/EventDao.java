package fr.iutinfo.skeleton.res;

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
 * Created by nicbe on 24/03/2016.
 */
public interface EventDao {
    @SqlUpdate("create table IF NOT EXISTS event (" +
            "id integer primary key auto_increment, " +
            "owner integer REFERENCES users(id), " +
            "location varchar(255), " +
            "date DATE, " +
            "name varchar(255), " +
            "limitTime date, " +
            "isLock BOOLEAN, " +
            "isRush BOOLEAN, " +
            "isTime BOOLEAN, " +
            "timeBeforeRush INTEGER ," +
            "cost INTEGER ," +
            "isDone BOOLEAN, " +
            "isCanceled BOOLEAN" +
            " )")
    void createEventTable();

    @SqlUpdate("insert into event (owner, location,date,name,limitTime,isLock,isRush,isTime,timeBeforeRush,cost,isDone,isCanceled)" +
            " values (:owner, :location,:date,:name,:limitTime,:isLock,:isRush,:isTime,:timeBeforeRush,:cost,:isDone,:isCanceled)")
    @GetGeneratedKeys
    int insert(@BindEvent() Event event);

    @SqlUpdate("UPDATE event SET" +
            "owner=:owner, location=:location,date=:date,name=:name,limitTime=:limitTime," +
            "isLock=:isLock,isRush=:isRush,isTime=:isTime,timeBeforeRush=:timeBeforeRush,cost=:cost,isDone=:isDone,isCanceled=:isCanceled" +
            " WHERE id=:id")
    @GetGeneratedKeys
    int updateEvent(@BindEvent() Event event);

    @SqlUpdate  ("DELETE from event where id= :id")
    void deleteEvent(@Bind("id") int id);


    @SqlQuery("select * from event where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Event findByName(@Bind("name") String name);

    @SqlQuery("select * from event where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Event findById(@Bind("id") int id);

    @SqlUpdate("drop table if exists event")
    void dropEventfTable();

    @SqlQuery("select * from event order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Event> all();


    @SqlQuery("select * from event where owner=:user order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Event> all(@Bind("user") int userId);


    void close();
}
