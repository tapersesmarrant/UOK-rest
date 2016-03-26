package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by nicbe on 24/03/2016.
 */
public class UserCategoryDTO {

    private int id;
    private int user;
    private String name;

    public UserCategoryDTO() {
    }

    public UserCategoryDTO(int id, int user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public UserCategoryDTO(int user, String name) {
        this.user = user;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCategoryDTO that = (UserCategoryDTO) o;
        return id == that.id &&
                user == that.user &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, user, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("name", name)
                .toString();
    }
}
