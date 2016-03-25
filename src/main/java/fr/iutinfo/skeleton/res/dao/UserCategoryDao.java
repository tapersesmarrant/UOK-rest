package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.Group;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.UserCategory;
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
public interface UserCategoryDao {
    @SqlUpdate("create table IF NOT EXISTS userCategory (" +

            "id INTEGER,"+
            "user VARCHAR(100), "+
            "name VARCHAR(100), "+
            "CONSTRAINT pk_group PRIMARY KEY (id,user,name),"+
            "CONSTRAINT fk_user FOREIGN KEY user REFERENCES  users(id)")
    void createUserTable();

    @SqlUpdate("insert into group (id, user,name)"+
            "values (:id, :user ,:name)")
    @GetGeneratedKeys
    int insert(@BindEvent() UserCategory userCategory);

    @SqlUpdate  ("DELETE from group where id=:id")
    void deleteInvit(@Bind("id") int id);


    @SqlUpdate("drop table if exists userCategory")
    void dropUserTable();

    @SqlQuery("select * from group where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Group findByid(@Bind("id") int id);
}
