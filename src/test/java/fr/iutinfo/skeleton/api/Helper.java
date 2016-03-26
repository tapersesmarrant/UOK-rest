package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.UserDTO;
import fr.iutinfo.skeleton.res.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Helper {
    final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private static UserDao dao;
    public WebTarget target;


    public Helper(WebTarget target) {
        this.target = target;
        dao = BDDFactory.getDbi().open(UserDao.class);
    }

    void initDb() {
        dao.dropUserTable();
        dao.createUserTable();
    }

    UserDTO createUserWithName(String name) {
        UserDTO user = new UserDTO(0, name);
        return doPost(user);
    }

    UserDTO createUserWithAlias(String name, String alias) {
        UserDTO user = new UserDTO(0, name, alias);
        return doPost(user);
    }


    UserDTO createUserWithEmail(String name, String email) {
        UserDTO user = new UserDTO(0, name);
        user.setEmail(email);
        return doPost(user);
    }

    UserDTO createUserWithPassword(String name, String passwd, String salt) {
        UserDTO user = new UserDTO(0, name);
        user.setSalt(salt);
        user.setPassword(passwd);
        logger.debug("createUserWithPassword Hash : " + user.getPasswdHash());
        return doPost(user);
    }

    UserDTO doPost(UserDTO user) {
        Entity<UserDTO> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
        return target.request().post(userEntity).readEntity(UserDTO.class);
    }

}
