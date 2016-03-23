/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iutinfo.skeleton.api;

import com.google.common.base.MoreObjects;
import java.security.Principal;
import java.sql.Date;

/**
 *
 * @author ouvryl
 */
public class Event {

    private int id=0;
    private int owner;
    private String location ;
    private Date date;
    private String name;
    private Date limiteTime;
    private boolean isLock;
    private boolean isRush;
    private boolean isTime;
    private int timeBeforeRush;
    private int cost;
    private boolean isDone =true;
    private boolean isCanceled =true;
    private String desciption;
    
    public Event(int id, int onwer, Date date, String description){
        this.id=id;
        this.date=date;
        this.owner=onwer;
        this.desciption=description;
                
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the limiteTime
     */
    public Date getLimiteTime() {
        return limiteTime;
    }

    /**
     * @param limiteTime the limiteTime to set
     */
    public void setLimiteTime(Date limiteTime) {
        this.limiteTime = limiteTime;
    }

    /**
     * @return the isLock
     */
    public boolean isLock() {
        return isLock;
    }

    /**
     * @param isLock the isLock to set
     */
    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    /**
     * @return the isRush
     */
    public boolean isRush() {
        return isRush;
    }

    /**
     * @param isRush the isRush to set
     */
    public void setIsRush(boolean isRush) {
        this.isRush = isRush;
    }

    /**
     * @return the isTime
     */
    public boolean isTime() {
        return isTime;
    }

    /**
     * @param isTime the isTime to set
     */
    public void setIsTime(boolean isTime) {
        this.isTime = isTime;
    }

    /**
     * @return the timeBeforeRush
     */
    public int getTimeBeforeRush() {
        return timeBeforeRush;
    }

    /**
     * @param timeBeforeRush the timeBeforeRush to set
     */
    public void setTimeBeforeRush(int timeBeforeRush) {
        this.timeBeforeRush = timeBeforeRush;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return the isDone
     */
    public boolean isIsDone() {
        return isDone;
    }

    /**
     * @param isDone the isDone to set
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * @return the isCanceled
     */
    public boolean isIsCanceled() {
        return isCanceled;
    }

    /**
     * @param isCanceled the isCanceled to set
     */
    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    /**
     * @return the desciption
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * @param desciption the desciption to set
     */
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
/*    return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("alias", alias)
                .add("id", id)
                .add("email", email)
                .add("password", password)
                .add("passwdHash", passwdHash)
                .add("salt", salt)
                .add("telNumber", telNumber)
                .add("isPro", isPro)
                .add("location", location)
                .add("isAcceptingGlobal", isAcceptingGlobal)
                .omitNullValues()
                .toString(); */
  
    
}
