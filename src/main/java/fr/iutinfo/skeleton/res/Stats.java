package fr.iutinfo.skeleton.res;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import fr.iutinfo.skeleton.api.BDDFactory;

/**
 * Created by nicbe on 24/03/2016.
 */
public class Stats {
    private int nmbUsers;

    public Stats(StatDao dao){
        nmbUsers = dao.getNmbUsers();
    }

    public Stats(){
        this(BDDFactory.getDbi().onDemand(StatDao.class));
    }

    public int getNmbUsers() {
        return nmbUsers;
    }

    public void setNmbUsers(int nmbUsers) {
        this.nmbUsers = nmbUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return nmbUsers == stats.nmbUsers;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nmbUsers);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nmbUsers", nmbUsers)
                .toString();
    }

}
