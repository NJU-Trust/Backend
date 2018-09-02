package nju.trust.payloads.target;

import nju.trust.entity.target.LargeProjectClassification;
import nju.trust.entity.target.LargeTarget;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetInfo extends TargetInfo {

    private byte[][] files;

    private LargeProjectClassification classification;

    public LargeTargetInfo(LargeTarget target) {
        super(target);
        classification = target.getClassification();
    }

    @Override
    public String toString() {
        return "LargeTargetInfo{" +
                "files=" + files +
                ", classification=" + classification +
                '}';
    }

    public byte[][] getFiles() {
        return files;
    }

    public void setFiles(byte[][] files) {
        this.files = files;
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }
}
