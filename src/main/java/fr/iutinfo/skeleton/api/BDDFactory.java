package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.dao.InvitDao;
import fr.iutinfo.skeleton.res.model.EventDTO;
import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.model.UserDTO;
import fr.iutinfo.skeleton.res.dao.UserDao;
import org.skife.jdbi.v2.DBI;
import org.h2.jdbcx.JdbcDataSource;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class BDDFactory {
    private static DBI dbi = null;

    private final static boolean TODROP=false;

    public static DBI getDbi() {
        if(dbi == null) {
            JdbcDataSource h2ds = new JdbcDataSource();
            String url = "jdbc:h2:"+ System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "data.db";
            System.out.println(url);
            h2ds.setURL(url);
            h2ds.setUser("dummyUser");
            h2ds.setPassword("I am a password");
            dbi = new DBI(h2ds);
            //*
            UserDao udao = dbi.open(UserDao.class);
            EventDao edao = dbi.open(EventDao.class);
            InvitDao invitDao = dbi.open(InvitDao.class);
            if (TODROP){
                udao.dropUserTable();
                edao.dropEventfTable();
                invitDao.dropInvitTable();
            }
            udao.createUserTable();
            if (udao.all().isEmpty()){
                //telNumber
                UserDTO dum = new UserDTO(1,"0000", "0000");
                dum.setPassword("0000");
                dum.setTelNumber("060011223344");
                dum.setEmail("a.b@c.fr");
                udao.insert(dum);
                dum = new UserDTO(2,"1111", "1111");
                dum.setPassword("1111");
                dum.setTelNumber("060011223344");
                dum.setEmail("a.bc@c.fr");
                udao.insert(dum);
            }
            System.out.println(udao.all());
            udao.close();
            //*
            edao.createEventTable();
            if (edao.all().isEmpty()){
                EventDTO dum = new EventDTO();
                dum.setOwner(1);
                dum.setLocation("in your mom home");
                dum.setTime(true);
                dum.setDate(new Date());
                dum.setName("Finaly, a freaking event...");
                dum.setLimiteTime(new Date());
                dum.setCost(0);
                edao.insert(dum);
                //dum.
            }
            System.out.println("All : " + edao.all());
            edao.close();

            invitDao.createUserTable();
            invitDao.close();
            //*/
        }
        return dbi;
    }


    public static void main(String... args){

        EventDao edao = getDbi().open(EventDao.class);
        EventDTO dum = new EventDTO();
        dum.setOwner(1);
        dum.setLocation("in your mom home");
        dum.setTime(true);
        dum.setDate(new Date());
        dum.setName("Finaly, a freaking event...");
        dum.setLimiteTime(new Date());
        dum.setCost(0);
        edao.insert(dum);

    }
}