package nju.trust.payloads.target;

import nju.trust.entity.target.SmallProjectClassification;
import nju.trust.entity.target.SmallTarget;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetInfo extends TargetInfo {

    private SmallProjectClassification classification;

    public SmallTargetInfo(SmallTarget target) {
        super(target);
        classification = target.getClassification();
    }

    @Override
    public String toString() {
        return "SmallTargetInfo{" +
                "classification=" + classification +
                '}';
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }

}
