package nju.trust.payloads.user;

import nju.trust.entity.user.Gender;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class ApplyIntermediateUserRequest {

    private String stuId;

    private MultipartFile stuCardImage;

    private MultipartFile idCardImage;

    private String idNumber;

    private Gender gender;

    private String realName;

    private Integer age;

    @Override
    public String toString() {
        return "ApplyIntermediateUserRequest{" +
                "stuId='" + stuId + '\'' +
                ", stuCardImage=" + stuCardImage +
                ", idCardImage=" + idCardImage +
                ", idNumber='" + idNumber + '\'' +
                ", gender=" + gender +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public MultipartFile getStuCardImage() {
        return stuCardImage;
    }

    public void setStuCardImage(MultipartFile stuCardImage) {
        this.stuCardImage = stuCardImage;
    }

    public MultipartFile getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(MultipartFile idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
