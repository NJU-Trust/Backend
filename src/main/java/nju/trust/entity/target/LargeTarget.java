package nju.trust.entity.target;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LARGE")
public class LargeTarget extends BaseTarget {

    /**
     * Official document and other description files
     */
    @ElementCollection(targetClass = String.class)
    private List<String> files;

    /**
     * Explanation for each fee
     */
    @ElementCollection(targetClass = FeeExplanation.class)
    private List<FeeExplanation> feeExplanations;

    private String repayPlan;

    private String repayApproach;

    private String projectDescription;

    @Override
    public String toString() {
        return "LargeTarget{" +
                "files=" + files +
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