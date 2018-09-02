package nju.trust.payloads.target;

import nju.trust.entity.target.LargeProjectClassification;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetRequest extends BasicTargetRequest{

    private LargeProjectClassification classification;

    @Override
    public String toString() {
        return "LargeTargetRequest{" +
                "basicInfo=" + super.toString() +
                ", classification=" + classification +
                '}';
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }
}
