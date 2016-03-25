package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.model.Group;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.utils.binders.BindEvent;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

/**
 * Created by ouvryl on 25/03/16.
 */
public interface GroupDao {
    @SqlUpdate("create table IF NOT EXISTS group (" +

            "id INTEGER,"+
            "owner INTEGER, " +
            "name VARCHAR(100), " +
            "fixedCategory  INTEGER , " +
            "isFirecategoryUser INTEGER, " +
            "CONSTRAINT pk_group PRIMARY KEY (id)" )
    void createUserTable();

    @SqlUpdate("insert into group (owner, name,fixedCategory ,categoryUser)"+
            "values (:owner, :name,:fixedCategory,:categoryUser)")
    @GetGeneratedKeys
    int insert(@BindEvent() Invit invit);

    @SqlUpdate  ("DELETE from group where id=:id")
    void deleteInvit(@Bind("id") int id);


    @SqlUpdate("drop table if exists group")
    void dropUserTable();

    @SqlQuery("select * from group where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Group findByid(@Bind("id") int id);
}
