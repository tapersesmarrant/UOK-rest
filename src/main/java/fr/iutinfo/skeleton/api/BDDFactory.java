package fr.iutinfo.skeleton.api;

import com.google.common.base.Strings;
import fr.iutinfo.skeleton.res.dao.InvitDao;
import fr.iutinfo.skeleton.res.model.Event;
import fr.iutinfo.skeleton.res.dao.EventDao;
import fr.iutinfo.skeleton.res.model.Invit;
import fr.iutinfo.skeleton.res.model.User;
import fr.iutinfo.skeleton.res.dao.UserDao;
import org.skife.jdbi.v2.DBI;
import org.h2.jdbcx.JdbcDataSource;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class BDDFactory {
    private static DBI dbi = null;

    private final static boolean TODROP=true;

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
                User dum = new User(1,"0000", "0000");
                dum.setPassword("0000");
                dum.setTelNumber("0000000000");
                dum.setEmail("aaaa@c.fr");
                udao.insert(dum);
                dum = new User(2,"1111", "1111");
                dum.setPassword("1111");
                dum.setTelNumber("1111111111");
                dum.setEmail("bbbb@c.fr");
                udao.insert(dum);
                dum = new User(2,"admin", "admin");
                dum.setPassword("admin");
                dum.setTelNumber("0681711827");
                dum.setEmail("ccc@c.fr");
                dum.setRole(User.Roles.admin);
                udao.insert(dum);
                dum = new User(2,"admin2", "admin2");
                dum.setPassword("admin2");
                dum.setTelNumber("0681211827");
                dum.setEmail("ccac@c.fr");
                dum.setRole(User.Roles.admin);
                udao.insert(dum);

                for (int i = 3; i < 100; i++){
                    String cur = Strings.repeat(""+i,4);
                    dum = new User(0, cur, cur);
                    dum.setPassword(cur);
                    dum.setTelNumber(Strings.repeat(cur, 2));
                    dum.setEmail(cur+"@dumy.fr");
                    udao.insert(dum);
                }
            }
            System.out.println(udao.all());
            udao.close();
            //*
            edao.createEventTable();
            if (edao.all().isEmpty()){
                Event dum = new Event();
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
            if (invitDao.all().isEmpty()){
                System.out.println("found Invit empty empty !");
                Invit invit = new Invit(1,3,new Date(),false,false);
                System.out.println("Inserting invit 2");
                invitDao.insert(invit);
                System.out.println("INserting invit 3");
                invit.setUser(4);
                invitDao.insert(invit);
                System.out.println("INserting invit 4");
                invit.setUser(5);
                invitDao.insert(invit);
                System.out.println("INserting invit 5");
                invit.setUser(6);
                invitDao.insert(invit);
                System.out.println("Inserted !");
            }
            System.out.println(invitDao.all());
            invitDao.close();
            //*/
        }
        return dbi;
    }


    public static void main(String... args){

        EventDao edao = getDbi().open(EventDao.class);

    }
}