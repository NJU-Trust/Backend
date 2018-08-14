package nju.trust.payloads.target;

import nju.trust.entity.UseClassification;
import nju.trust.entity.target.SmallTarget;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetInfo extends TargetInfo {

    private List<UseClassification> purpose;

    private List<String> purposeFiles;

    private String purposeExplanation;

    public SmallTargetInfo(SmallTarget target) {
        super(target);
        purpose = target.getPurpose();
        purposeFiles = target.getPurposeFiles();
        purposeExplanation = target.getPurposeExplanation();
    }

    @Override
    public String toString() {
        return "SmallTargetInfo{" +
                ", purpose=" + purpose +
                ", purposeFiles=" + purposeFiles +
                ", purposeExplanation='" + purposeExplanation + '\'' +
                '}';
    }

    public List<UseClassification> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<UseClassification> purpose) {
        this.purpose = purpose;
    }

    public List<String> getPurposeFiles() {
        return purposeFiles;
    }

    public void setPurposeFiles(List<String> purposeFiles) {
        this.purposeFiles = purposeFiles;
    }

    public String getPurposeExplanation() {
        return purposeExplanation;
    }

    public void setPurposeExplanation(String purposeExplanation) {
        this.purposeExplanation = purposeExplanation;
    }
}
