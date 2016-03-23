package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;
import org.h2.jdbcx.JdbcDataSource;

import javax.inject.Singleton;

@Singleton
public class BDDFactory {
    private static DBI dbi = null;

    public static DBI getDbi() {
        if(dbi == null) {
            JdbcDataSource h2ds = new JdbcDataSource();
            String url = "jdbc:h2:"+ System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "data.db";
            System.out.println(url);
            h2ds.setURL(url);
            h2ds.setUser("dummyUser");
            h2ds.setPassword("I am a password");
            dbi = new DBI(h2ds);
        }
        return dbi;
    }
}