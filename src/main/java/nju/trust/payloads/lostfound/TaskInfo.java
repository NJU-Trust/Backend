package nju.trust.payloads.lostfound;

import com.fasterxml.jackson.annotation.JsonProperty;
import nju.trust.entity.lostfound.LostAndFound;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public class TaskInfo {

    private long taskID;

    private String username;

    private String state;

    private LocalDate date;

    private String property;

    private String thingsType;

    private String thingsName;

    private String phone;

    private String picPath;

    private String description;

    private String lostPlace;

    public TaskInfo() {

    }

    public TaskInfo(String username,String property, String thingsType, String thingsName, String phone, String picPath, String description, String lostPlace) {
        this.username = username;
        this.property = property;
        this.thingsType = thingsType;
        this.thingsName = thingsName;
        this.phone = phone;
        this.picPath = picPath;
        this.description = description;
        this.lostPlace = lostPlace;
    }

    public TaskInfo(LostAndFound lostAndFound){
        this.taskID = lostAndFound.getId();
        this.username = lostAndFound.getUsername();
        this.state = lostAndFound.getState().getStr();
        this.date = lostAndFound.getDate();
        this.property = lostAndFound.getProperty().getStr();
        this.thingsType = lostAndFound.getThingsType().getStr();
        this.thingsName = lostAndFound.getThingsName();
        this.phone = lostAndFound.getPhone();
        this.picPath = lostAndFound.getPicPath();
        this.description = lostAndFound.getDescription();
        this.lostPlace = lostAndFound.getLostPlace().getStr();
    }
    public TaskInfo(UploadLostAndFoundRequest lostAndFound){
        this.property = MsgProperty.getMsgProperty(lostAndFound.getProperty()).getStr();
        this.thingsType = ThingsType.getThingsType(lostAndFound.getThingsType()).getStr();
        this.thingsName = lostAndFound.getThingsName();
        this.phone = lostAndFound.getPhone();
        this.picPath = lostAndFound.getPicPath();
        this.description = lostAndFound.getDescription();
        this.lostPlace = LostPlace.getLostPlace(lostAndFound.getLostPlace()).getStr();
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getThingsName() {
        return thingsName;
    }

    public void setThingsName(String thingsName) {
        this.thingsName = thingsName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getThingsType() {
        return thingsType;
    }

    public void setThingsType(String thingsType) {
        this.thingsType = thingsType;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    public void print(){
        System.out.println("taskID:"+this.taskID);
        System.out.println("username:"+this.username);
        System.out.println("description:"+this.description);
        System.out.println("lostPlace:"+this.lostPlace);
    }
}
