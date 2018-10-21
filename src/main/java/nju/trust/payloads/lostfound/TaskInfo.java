package nju.trust.payloads.lostfound;

import nju.trust.entity.lostfound.LostAndFound;

import java.time.LocalDateTime;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public class TaskInfo {

    private long taskID;

    private String username;

    private ProcessState state;

    private LocalDateTime date;

    private MsgProperty property;

    private ThingsType thingsType;

    private String thingsName;

    private String phone;

    private String picPath;

    private String description;

    private LostPlace lostPlace;

    public TaskInfo() {

    }

    public TaskInfo(String username,String property, String thingsType, String thingsName, String phone, String picPath, String description, String lostPlace) {
        this.username = username;
        this.property = MsgProperty.getMsgProperty(property);
        this.thingsType = ThingsType.getThingsType(thingsType);
        this.thingsName = thingsName;
        this.phone = phone;
        this.picPath = picPath;
        this.description = description;
        this.lostPlace = LostPlace.getLostPlace(lostPlace);
    }

    public TaskInfo(LostAndFound lostAndFound){
        this.taskID = lostAndFound.getId();
        this.username = lostAndFound.getUser().getUsername();
        this.state = lostAndFound.getState();
        this.date = lostAndFound.getDate();
        this.property = lostAndFound.getProperty();
        this.thingsType = lostAndFound.getThingsType();
        this.thingsName = lostAndFound.getThingsName();
        this.phone = lostAndFound.getPhone();
        this.picPath = lostAndFound.getPicPath();
        this.description = lostAndFound.getDescription();
        this.lostPlace = lostAndFound.getLostPlace();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public MsgProperty getProperty() {
        return property;
    }

    public void setProperty(MsgProperty property) {
        this.property = property;
    }

    public ThingsType getThingsType() {
        return thingsType;
    }

    public void setThingsType(ThingsType thingsType) {
        this.thingsType = thingsType;
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

    public LostPlace getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(LostPlace lostPlace) {
        this.lostPlace = lostPlace;
    }

    public void print(){
        System.out.println("taskID:"+this.taskID);
        System.out.println("username:"+this.username);
        System.out.println("description:"+this.description);
        System.out.println("lostPlace:"+this.lostPlace);
    }
}
