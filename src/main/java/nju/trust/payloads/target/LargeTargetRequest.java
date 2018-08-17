package nju.trust.payloads.target;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.entity.LargeProjectClassification;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetRequest {

    @JsonUnwrapped
    private BasicTargetRequest basicInfo;

    private LargeProjectClassification classification;

    @Override
    public String toString() {
        return "LargeTargetRequest{" +
                "basicInfo=" + basicInfo +
                ", classification=" + classification +
                '}';
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }

    public BasicTargetRequest getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicTargetRequest basicInfo) {
        this.basicInfo = basicInfo;
    }
}
