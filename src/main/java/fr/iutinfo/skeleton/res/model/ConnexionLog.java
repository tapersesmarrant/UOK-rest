package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * Created by nicbe on 24/03/2016.
 */
public class ConnexionLog {

    private int id;;
    private Date timestamp;
    private int user;


    public ConnexionLog(int id, Date timestamp, int user) {
        this.id = id;
        this.timestamp = timestamp;
        this.user = user;
    }

    public ConnexionLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnexionLog that = (ConnexionLog) o;
        return id == that.id &&
                user == that.user &&
                Objects.equal(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, timestamp, user);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("timestamp", timestamp)
                .add("user", user)
                .toString();
    }
}
