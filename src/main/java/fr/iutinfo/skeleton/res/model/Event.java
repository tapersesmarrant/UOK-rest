/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iutinfo.skeleton.res.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ouvryl beaussart
 */
public class Event {

    private int id=0;
    private int owner;
    private String location;
    private Date date;
    private String name;
    private Date limiteTime;
    private boolean isLock=false;
    private boolean isRush;
    private boolean isTime;
    private int timeBeforeRush;
    private int cost;
    private int nmbPlaces;
    private boolean isDone =false;
    private boolean isCanceled =false;
    private String desciption;
    private List<Invit> invit;

    public Event(){}
    
    public Event(int id, int onwer, Date date, String description){
        this.id=id;
        this.date=date;
        this.owner=onwer;
        this.desciption=description;
                
        
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLimiteTime() {
        return limiteTime;
    }

    public void setLimiteTime(Date limiteTime) {
        this.limiteTime = limiteTime;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isRush() {
        return isRush;
    }

    public void setRush(boolean rush) {
        isRush = rush;
    }

    public boolean isTime() {
        return isTime;
    }

    public void setTime(boolean time) {
        isTime = time;
    }

    public int getTimeBeforeRush() {
        return timeBeforeRush;
    }

    public void setTimeBeforeRush(int timeBeforeRush) {
        this.timeBeforeRush = timeBeforeRush;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                owner == event.owner &&
                isLock == event.isLock &&
                isRush == event.isRush &&
                isTime == event.isTime &&
                timeBeforeRush == event.timeBeforeRush &&
                cost == event.cost &&
                nmbPlaces == event.nmbPlaces &&
                isDone == event.isDone &&
                isCanceled == event.isCanceled &&
                Objects.equal(location, event.location) &&
                Objects.equal(date, event.date) &&
                Objects.equal(name, event.name) &&
                Objects.equal(limiteTime, event.limiteTime) &&
                Objects.equal(desciption, event.desciption);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, owner, location, date, name, limiteTime, isLock, isRush, isTime, timeBeforeRush, cost, nmbPlaces, isDone, isCanceled, desciption);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("owner", owner)
                .add("location", location)
                .add("date", date)
                .add("name", name)
                .add("limiteTime", limiteTime)
                .add("isLock", isLock)
                .add("isRush", isRush)
                .add("isTime", isTime)
                .add("timeBeforeRush", timeBeforeRush)
                .add("cost", cost)
                .add("nmbPlaces", nmbPlaces)
                .add("isDone", isDone)
                .add("isCanceled", isCanceled)
                .add("desciption", desciption)
                .add("invit", invit)
                .toString();
    }

    public List<Invit> getInvit() {
        return invit;
    }

    public void setInvit(List<Invit> invit) {
        this.invit = invit;
    }
}
