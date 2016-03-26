package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by nicbe on 24/03/2016.
 */
public class InterestedCategory {
    private int id;
    private int user;
    private int category;

    public InterestedCategory(int id, int user, int category) {
        this.id = id;
        this.user = user;
        this.category = category;
    }

    public InterestedCategory(int user, int category) {
        this.user = user;
        this.category = category;
    }

    public InterestedCategory() {
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestedCategory that = (InterestedCategory) o;
        return id == that.id &&
                user == that.user &&
                category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, user, category);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("category", category)
                .toString();
    }
}
