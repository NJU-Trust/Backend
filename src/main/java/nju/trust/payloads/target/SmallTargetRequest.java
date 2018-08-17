package nju.trust.payloads.target;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.entity.SmallProjectClassification;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class SmallTargetRequest {

    @JsonUnwrapped
    private BasicTargetRequest basicInfo;

    private SmallProjectClassification classification;

    private Double maximumAmount;

    @Override
    public String toString() {
        return "SmallTargetRequest{" +
                "basicInfo=" + basicInfo +
                ", classification=" + classification +
                ", maximumAmount=" + maximumAmount +
                '}';
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

    public BasicTargetRequest getBasicInfo() {

        return basicInfo;
    }

    public void setBasicInfo(BasicTargetRequest basicInfo) {
        this.basicInfo = basicInfo;
    }
}
