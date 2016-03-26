package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by nicbe on 24/03/2016.
 */
public class GroupAppartenanceDTO {
    private int group;
    private int user;

    public GroupAppartenanceDTO() {
    }

    public GroupAppartenanceDTO(int group, int user) {
        this.group = group;
        this.user = user;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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
        GroupAppartenanceDTO that = (GroupAppartenanceDTO) o;
        return group == that.group &&
                user == that.user;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(group, user);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("group", group)
                .add("user", user)
                .toString();
    }
}
