package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by nicbe on 24/03/2016.
 */
public class GroupDTO {
    private int id;
    private int owner;
    private String name;
    private int fixedCategory;
    private int categoryUser;

    public GroupDTO() {
    }

    public GroupDTO(int id, int owner, String name, int fixedCategory, int categoryUser) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.fixedCategory = fixedCategory;
        this.categoryUser = categoryUser;
    }

    public GroupDTO(int owner, String name, int fixedCategory, int categoryUser) {
        this.owner = owner;
        this.name = name;
        this.fixedCategory = fixedCategory;
        this.categoryUser = categoryUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFixedCategory() {
        return fixedCategory;
    }

    public void setFixedCategory(int fixedCategory) {
        this.fixedCategory = fixedCategory;
    }

    public int getCategoryUser() {
        return categoryUser;
    }

    public void setCategoryUser(int categoryUser) {
        this.categoryUser = categoryUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO group = (GroupDTO) o;
        return id == group.id &&
                owner == group.owner &&
                fixedCategory == group.fixedCategory &&
                categoryUser == group.categoryUser &&
                Objects.equal(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, owner, name, fixedCategory, categoryUser);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("owner", owner)
                .add("name", name)
                .add("fixedCategory", fixedCategory)
                .add("categoryUser", categoryUser)
                .toString();
    }
}
