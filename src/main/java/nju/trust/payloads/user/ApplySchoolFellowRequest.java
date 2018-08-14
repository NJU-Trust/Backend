package nju.trust.payloads.user;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class ApplySchoolFellowRequest {

    private MultipartFile[] diplomas;

    @Override
    public String toString() {
        return "ApplySchoolFellowRequest{" +
                "diplomas=" + Arrays.toString(diplomas) +
                '}';
    }

    public MultipartFile[] getDiplomas() {
        return diplomas;
    }

    public void setDiplomas(MultipartFile[] diplomas) {
        this.diplomas = diplomas;
    }
}
