package nju.trust.payloads.target;

import nju.trust.entity.LargeProjectClassification;
import nju.trust.entity.target.FeeExplanation;
import nju.trust.entity.target.LargeTarget;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetInfo extends TargetInfo {

    private List<String> files;

    private LargeProjectClassification classification;

    public LargeTargetInfo(LargeTarget target) {
        super(target);
        files = target.getFiles();
        classification = target.getClassification();
    }

    @Override
    public String toString() {
        return "LargeTargetInfo{" +
                "files=" + files +
                ", classification=" + classification +
                '}';
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }
}
