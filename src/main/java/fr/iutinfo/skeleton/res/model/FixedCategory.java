package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by nicbe on 24/03/2016.
 */
public class FixedCategory {
    private int id;
    private String name;
    private String icon;

    public FixedCategory(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public FixedCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FixedCategory(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedCategory that = (FixedCategory) o;
        return id == that.id &&
                Objects.equal(name, that.name) &&
                Objects.equal(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, icon);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("icon", icon)
                .toString();
    }
}
