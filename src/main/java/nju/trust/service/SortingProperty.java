package nju.trust.service;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public enum SortingProperty {

    MONEY("money"),

    START_TIME("startTime"),

    INTEREST_RATE("interestPlan"),

    REPAYMENT_DURATION("repaymentDuration");

    private String property;

    SortingProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
