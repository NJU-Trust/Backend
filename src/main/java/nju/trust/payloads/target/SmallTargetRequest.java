package nju.trust.payloads.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.target.SmallProjectClassification;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetRequest extends BasicTargetRequest {


    private SmallProjectClassification classification;

    private Double maximumAmount;

    private IdentityOption identityOption;

    @Override
    public String toString() {
        return "SmallTargetRequest{" +
                "classification=" + classification +
                ", maximumAmount=" + maximumAmount +
                ", identityOption=" + identityOption +
                '}';
    }

    public IdentityOption getIdentityOption() {
        return identityOption;
    }

    public void setIdentityOption(IdentityOption identityOption) {
        this.identityOption = identityOption;
    }

    public Double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }
}
