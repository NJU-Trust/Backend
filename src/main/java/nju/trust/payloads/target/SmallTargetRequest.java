package nju.trust.payloads.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.target.SmallProjectClassification;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetRequest extends BasicTargetRequest {


    private SmallProjectClassification classification;

    private IdentityOption identityOption;

    @Override
    public String toString() {
        return "SmallTargetRequest{" +
                "classification=" + classification +
                ", identityOption=" + identityOption +
                '}';
    }

    public IdentityOption getIdentityOption() {
        return identityOption;
    }

    public void setIdentityOption(IdentityOption identityOption) {
        this.identityOption = identityOption;
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }
}
