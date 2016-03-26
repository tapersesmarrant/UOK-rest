package fr.iutinfo.skeleton.res.dao;

import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.utils.binders.BindUser;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
	@SqlUpdate("create table IF NOT EXISTS users (" +
            "id integer primary key auto_increment, " +
            "name varchar(100), " +
            "alias varchar(100), " +
            "email varchar(100), " +
            "passwdHash varchar(32), " +
            "salt varchar(32), " +
            "telNumber varchar(32), " +
            "isPro BOOLEAN, " +
            "location VARCHAR(32)," +
            "global BOOLEAN )")
	void createUserTable();

	@SqlUpdate("insert into users (name,alias ,email,isPro, telNumber, passwdHash, salt, location, global) values (:name, :alias, :email, :isPro, :telNumber, :hash, :salt, :location, :isAcceptingGlobal)")
	@GetGeneratedKeys
	int insert(@BindUser() User user);

	@SqlUpdate("UPDATE users SET name=:name ,alias=:alias ,email=:email,isPro=:isPro, telNumber=:telNumber, passwdHash=:hash, salt=:salt, location=:location, global=:isAcceptingGlobal WHERE id=:id")
	@GetGeneratedKeys
	int updateUser(@BindUser() User user);

    @SqlUpdate  ("DELETE from users where id= :id")
    void deleteUser(@Bind("id") int id);


	@SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("name") String name);

	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 

	@SqlQuery("select * from users order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<User> all();

	@SqlQuery("select * from users where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);


	@SqlQuery("select * from users where email = :email")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByEmail(@Bind("email") String  email);


	@SqlQuery("select * from users where telNumber = :telNumber")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByNumber(@Bind("telNumber") String telNumber);

	void close();
}
