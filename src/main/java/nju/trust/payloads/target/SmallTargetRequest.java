package nju.trust.payloads.target;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.entity.UseClassification;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetRequest {

    @JsonUnwrapped
    private TargetInfo basicInfo;

    private UseClassification[] purpose;

    private MultipartFile[] purposeFiles;

    private String purposeExplanation;

    @Override
    public String toString() {
        return "SmallTargetRequest{" +
                "basicInfo=" + basicInfo +
                ", purpose=" + Arrays.toString(purpose) +
                ", purposeFiles=" + Arrays.toString(purposeFiles) +
                ", purposeExplanation='" + purposeExplanation + '\'' +
                '}';
    }

    public TargetInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(TargetInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public UseClassification[] getPurpose() {
        return purpose;
    }

    public void setPurpose(UseClassification[] purpose) {
        this.purpose = purpose;
    }

    public MultipartFile[] getPurposeFiles() {
        return purposeFiles;
    }

    public void setPurposeFiles(MultipartFile[] purposeFiles) {
        this.purposeFiles = purposeFiles;
    }

    public String getPurposeExplanation() {
        return purposeExplanation;
    }

    public void setPurposeExplanation(String purposeExplanation) {
        this.purposeExplanation = purposeExplanation;
    }
}
