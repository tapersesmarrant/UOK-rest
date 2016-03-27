package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

/**
 * Created by nicbe on 24/03/2016.
 */
public class Invit {
    private int event;
    private int user;
    private Date timestamp;
    private boolean isSecondaryList;
    private boolean isFired;
    private boolean isOk;
    private String nameEvent;
    private User userObject;

    public Invit() {
    }

    public Invit(int event, int user, Date timestamp, boolean isSecondaryList, boolean isFired) {
        this.event = event;
        this.user = user;
        this.timestamp = timestamp;
        this.isSecondaryList = isSecondaryList;
        this.isFired = isFired;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSecondaryList() {
        return isSecondaryList;
    }

    public void setSecondaryList(boolean secondaryList) {
        isSecondaryList = secondaryList;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invit invit = (Invit) o;
        return event == invit.event &&
                user == invit.user &&
                isSecondaryList == invit.isSecondaryList &&
                isFired == invit.isFired &&
                Objects.equal(timestamp, invit.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(event, user, timestamp, isSecondaryList, isFired);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("event", event)
                .add("user", user)
                .add("timestamp", timestamp)
                .add("isSecondaryList", isSecondaryList)
                .add("isFired", isFired)
                .toString();
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public User getUserObject() {
        return userObject;
    }

    public void setUserObject(User userObject) {
        this.userObject = userObject;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
