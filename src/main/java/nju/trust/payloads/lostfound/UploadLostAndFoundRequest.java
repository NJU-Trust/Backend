package nju.trust.payloads.lostfound;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/22
 * @Todo:
 */
public class UploadLostAndFoundRequest {

    private MsgProperty property;

    private ThingsType thingsType;

    private String thingsName;

    private String phone;

    private String picPath;

    private String description;

    private LostPlace lostPlace;


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
