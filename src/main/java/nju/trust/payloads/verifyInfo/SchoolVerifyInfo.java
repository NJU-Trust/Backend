package nju.trust.payloads.verifyInfo;

import nju.trust.entity.user.Gender;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class SchoolVerifyInfo {
    //姓名
    private String realName;
    //性别
    private Gender gender;
    //出生日期
    private String brithday;
    //身份证号
    private String idCardNumber;
    //学校
    private String university;
    //学院
    private String institution;
    //专业
    private String major;
    //
    private String alipay;
    //学生证照片
    private String stuCardImage;
    //校园卡照片路径
    private String schoolCardImage;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getStuCardImage() {
        return stuCardImage;
    }

    public void setStuCardImage(String stuCardImage) {
        this.stuCardImage = stuCardImage;
    }

    public String getSchoolCardImage() {
        return schoolCardImage;
    }

    public void setSchoolCardImage(String schoolCardImage) {
        this.schoolCardImage = schoolCardImage;
    }
}
