package nju.trust.payloads.user;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class ApplyIntermediateUserRequest {

    private String stuId;

    private MultipartFile stuCardImage;

    private String major;

    private Integer grade;

    @Override
    public String toString() {
        return "ApplyIntermediateUserRequest{" +
                "stuId='" + stuId + '\'' +
                ", stuCardImage=" + stuCardImage +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                '}';
    }

    public MultipartFile getStuCardImage() {
        return stuCardImage;
    }

    public void setStuCardImage(MultipartFile stuCardImage) {
        this.stuCardImage = stuCardImage;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
