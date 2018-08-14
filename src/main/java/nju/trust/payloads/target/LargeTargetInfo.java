package nju.trust.payloads.target;

import nju.trust.entity.target.FeeExplanation;
import nju.trust.entity.target.LargeTarget;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetInfo extends TargetInfo {

    private List<String> files;

    /**
     * Explanation for each fee
     */
    private List<FeeExplanation> feeExplanations;

    private String repayPlan;

    private String repayApproach;

    private String projectDescription;

    public LargeTargetInfo(LargeTarget target) {
        super(target);
        files = target.getFiles();
        feeExplanations = target.getFeeExplanations();
        repayPlan = target.getRepayPlan();
        repayApproach = target.getRepayApproach();
        projectDescription = target.getProjectDescription();
    }

    @Override
    public String toString() {
        return "LargeTargetInfo{" +
                ", files=" + files +
                ", feeExplanations=" + feeExplanations +
                ", repayPlan='" + repayPlan + '\'' +
                ", repayApproach='" + repayApproach + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<FeeExplanation> getFeeExplanations() {
        return feeExplanations;
    }

    public void setFeeExplanations(List<FeeExplanation> feeExplanations) {
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
