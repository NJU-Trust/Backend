package nju.trust.payloads.flea;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
public class UploadGoodsRequest {
    private String name;

    private String type;

    private String contact;

    private String picPath;

    private String desc;

    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UploadGoodsRequest{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", contact='" + contact + '\'' +
                ", picPath='" + picPath + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
