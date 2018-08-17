package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.TargetType;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class SimpleTarget {

    private String name;

    private String id;

    private CreditRating creditRating;

    private TargetType targetType;

    public SimpleTarget(String name, String id, CreditRating creditRating, TargetType targetType) {
        this.name = name;
        this.id = id;
        this.creditRating = creditRating;
        this.targetType = targetType;
    }

    @Override
    public String toString() {
        return "SimpleTarget{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", creditRating=" + creditRating +
                ", targetType=" + targetType +
                '}';
    }
}
