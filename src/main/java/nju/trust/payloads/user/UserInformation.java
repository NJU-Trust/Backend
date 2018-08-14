package nju.trust.payloads.user;

import nju.trust.entity.UserLevel;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class UserInformation {

    private String username;

    /**
     * User's real name
     */
    private String name;

    /**
     * User's avatar
     */
    private String picture;

    private String email;

    private String phone;

    private Integer grade;

    private String major;

    private String stuNum;

    /**
     * Student's id card number
     */
    private String stuId;

    /**
     * Platform's evaluation of this user
     */
    private String comment;

    private String stuCardImage;

    private String idCardImage;

    private String diplomaImage;

    @Override
    public String toString() {
        return "UserInformation{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", stuId='" + stuId + '\'' +
                ", comment='" + comment + '\'' +
                ", stuCardImage='" + stuCardImage + '\'' +
                ", idCardImage='" + idCardImage + '\'' +
                ", diplomaImage='" + diplomaImage + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStuCardImage() {
        return stuCardImage;
    }

    public void setStuCardImage(String stuCardImage) {
        this.stuCardImage = stuCardImage;
    }

    public String getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(String idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getDiplomaImage() {
        return diplomaImage;
    }

    public void setDiplomaImage(String diplomaImage) {
        this.diplomaImage = diplomaImage;
    }
}
