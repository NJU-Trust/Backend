package nju.trust.payloads.user;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class ApplyCompleteUserRequest {

    private String idNumber;

    private MultipartFile idCardImage;

    private String realName;

    @Override
    public String toString() {
        return "ApplyCompleteUserRequest{" +
                "idNumber='" + idNumber + '\'' +
                ", idCardImage=" + idCardImage +
                ", realName='" + realName + '\'' +
                '}';
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public MultipartFile getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(MultipartFile idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
