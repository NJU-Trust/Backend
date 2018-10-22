
package nju.trust.entity.lostfound;

import nju.trust.entity.user.User;
import nju.trust.payloads.lostfound.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
@Entity
public class LostAndFound {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private ProcessState state;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MsgProperty property;

    @Enumerated(EnumType.STRING)
    private ThingsType thingsType;

    private String thingsName;

    private String phone;

    private String picPath;

    private String description;

    @Enumerated(EnumType.STRING)
    private LostPlace lostPlace;

    private String toUsername;


    public LostAndFound() {

    }

    public LostAndFound(String username, ProcessState state, LocalDate date, MsgProperty property, ThingsType thingsType, String thingsName, String phone, String picPath, String description,LostPlace lostPlace) {
        this.username = username;
        this.state = state;
        this.date = date;
        this.property = property;
        this.thingsType = thingsType;
        this.thingsName = thingsName;
        this.phone = phone;
        this.picPath = picPath;
        this.description = description;
        this.lostPlace = lostPlace;
    }

    public LostAndFound(@NotNull TaskInfo taskInfo, String username){
        this.username = username;
        this.state = ProcessState.getProcessState(taskInfo.getState());
        this.date = taskInfo.getDate();
        this.property = MsgProperty.getMsgProperty(taskInfo.getProperty());
        this.thingsType = ThingsType.getThingsType(taskInfo.getThingsType());
        this.thingsName = taskInfo.getThingsName();
        this.phone = taskInfo.getPhone();
        this.picPath = taskInfo.getPicPath();
        this.description = taskInfo.getDescription();
        this.lostPlace = LostPlace.getLostPlace(taskInfo.getLostPlace());
    }


    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}

