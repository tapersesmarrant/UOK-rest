package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.GroupDTO;
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
            "categoryUser INTEGER, " +
            "CONSTRAINT pk_group PRIMARY KEY (id),"+
            "CONSTRAINT fk_categoryUser FOREIGN KEY categoryUser REFERENCES  userCategory(id)"+
            "CONSTRAINT fk_fixedCategory FOREIGN KEY fixedCategory REFERENCES fixedCategory(id))"+
            "CONSTRAINT fk_owner FOREIGN KEY owner REFERENCES users(id)")
    void createUserTable();

    @SqlUpdate("insert into group (owner, name,fixedCategory ,categoryUser)"+
            "values (:owner, :name,:fixedCategory,:categoryUser)")
    @GetGeneratedKeys
    int insert(@BindEvent() GroupDTO group);

    @SqlUpdate  ("DELETE from group where id=:id")
    void deleteInvit(@Bind("id") int id);


    @SqlUpdate("drop table if exists group")
    void dropUserTable();

    @SqlQuery("select * from group where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    GroupDTO findByid(@Bind("id") int id);
}
