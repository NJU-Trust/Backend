package nju.trust.payloads.verifyInfo;

import nju.trust.entity.user.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class SchoolVerifyInfo {
    //姓名
    private String realName;
    //性别
    private String gender;
    //出生日期
    private String birthday;
    //身份证号
    private String idCardNumber;
    //学校
    private String university;
    //学院
    private String institution;
    //专业
    private String major;
    //支付宝账号
    private String alipay;
    //学生证照片
    private String stuCardImage;
    //校园卡照片路径
    private String schoolCardImage;

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Gender getGender() {
        return Gender.getGender(gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return LocalDate.parse(birthday.split("T")[0], fmt);
    }

    public void setBirthday(String birthday) {
        this.birthday=birthday;
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

    public boolean hasNull() {
        if(realName == null || realName.length() == 0 ||
                gender == null ||
                birthday == null ||
                idCardNumber == null || (idCardNumber.length() != 15 && idCardNumber.length() != 18) ||
                university == null || university.length() == 0 ||
                institution == null || institution.length() == 0 ||
                major == null || major.length() == 0 ||
                alipay == null || alipay.length() == 0 ||
                stuCardImage == null || stuCardImage.length() == 0 ||
                schoolCardImage == null || schoolCardImage.length() == 0) {
            return true;
        }
        return false;
    }
}
