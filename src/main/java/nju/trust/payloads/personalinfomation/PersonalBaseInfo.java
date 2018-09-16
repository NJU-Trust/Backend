package nju.trust.payloads.personalinfomation;

import nju.trust.entity.UserLevel;
import nju.trust.entity.user.Gender;
import nju.trust.entity.user.User;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/12
 */
public class PersonalBaseInfo {

    private String username;    //用户姓名（从传入的参直接获得）

    private Gender gender;  //性别

    private Integer age;    //年龄

    private UserLevel userLevel;    //账号等级

    private String studentId;   //学号

    private Integer grade;      //年级

    private String major;       //专业

    private String phoneNumber;       //手机号

    private String alipay;      //支付宝账号

    private String email;       //邮箱

    private String institution;     //学院 or 工作单位

    private String livingPlace;     //现居地

    public PersonalBaseInfo(User user){
        this.gender = user.getGender();
        this.age = user.getAge();
        this.userLevel = user.getUserLevel();
        this.studentId = user.getStudentId();
        this.grade = user.getGrade();
        this.major = user.getMajor();
        this.phoneNumber = user.getPhoneNumber();
        this.alipay = user.getAlipay();
        this.email = user.getEmail();
        this.institution = user.getInstitution();
        this.livingPlace = user.getLivingPlace();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }
}
