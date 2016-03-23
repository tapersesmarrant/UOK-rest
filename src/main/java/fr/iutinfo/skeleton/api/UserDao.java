package fr.iutinfo.skeleton.api;

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
            "isAcceptingGlobal BOOLEAN )")
	void createUserTable();

	@SqlUpdate("insert into users (name,alias ,email,isPro, telNumber, passwdHash, salt, location, isAcceptingGlobal) values (:name, :alias, :email, :isPro, :telNumber, :passwdHash, :salt, :location, :isAcceptingGlobal)")
	@GetGeneratedKeys
	int insert(@BindBean() User user);


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

	void close();
}
