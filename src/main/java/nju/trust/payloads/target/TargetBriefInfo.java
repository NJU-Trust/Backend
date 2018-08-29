package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.TargetType;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class TargetBriefInfo {

    private String name;

    private String id;

    private CreditRating creditRating;

    private TargetType targetType;

    public TargetBriefInfo(String name, String id, CreditRating creditRating, TargetType targetType) {
        this.name = name;
        this.id = id;
        this.creditRating = creditRating;
        this.targetType = targetType;
    }

    @Override
    public String toString() {
        return "TargetBriefInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", creditRating=" + creditRating +
                ", targetType=" + targetType +
                '}';
    }
}
