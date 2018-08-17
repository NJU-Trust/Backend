package nju.trust.payloads.target;

import nju.trust.entity.SmallProjectClassification;
import nju.trust.entity.target.SmallTarget;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetInfo extends TargetInfo {

    private SmallProjectClassification classification;

    /**
     * 可承受额外费用偏好（希望为这笔融资至多付多少费用）
     */
    private Double maximumAmount;

    public SmallTargetInfo(SmallTarget target) {
        super(target);
        classification = target.getClassification();
        maximumAmount = target.getMaximumAmount();
    }

    @Override
    public String toString() {
        return "SmallTargetInfo{" +
                "classification=" + classification +
                ", maximumAmount=" + maximumAmount +
                '}';
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }

    public Double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
}
