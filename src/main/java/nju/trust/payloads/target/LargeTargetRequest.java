package nju.trust.payloads.target;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.entity.target.FeeExplanation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetRequest {

    @JsonUnwrapped
    private TargetInfo basicInfo;

    private MultipartFile[] files;

    /**
     * Explanation for each fee
     */
    private FeeExplanation[] feeExplanations;

    private String repayPlan;

    private String repayApproach;

    private String projectDescription;

    @Override
    public String toString() {
        return "LargeTargetRequest{" +
                "basicInfo=" + basicInfo +
                ", files=" + Arrays.toString(files) +
                ", feeExplanations=" + Arrays.toString(feeExplanations) +
                ", repayPlan='" + repayPlan + '\'' +
                ", repayApproach='" + repayApproach + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }

    public TargetInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(TargetInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public FeeExplanation[] getFeeExplanations() {
        return feeExplanations;
    }

    public void setFeeExplanations(FeeExplanation[] feeExplanations) {
        this.feeExplanations = feeExplanations;
    }

    public String getRepayPlan() {
        return repayPlan;
    }

    public void setRepayPlan(String repayPlan) {
        this.repayPlan = repayPlan;
    }

    public String getRepayApproach() {
        return repayApproach;
    }

    public void setRepayApproach(String repayApproach) {
        this.repayApproach = repayApproach;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
