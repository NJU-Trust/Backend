package nju.trust.entity;

/**
 * Author: J.D. Liao
 * Date: 2018/8/9
 */
public enum CheckState {

    /**
     * Check is on going
     */
    ONGING("审核中"),

    /**
     * The application has been approved
     */
    PASS("通过"),

    /**
     * The application was rejected
     */
    REJECT("未通过"),

    /**
     * 用户在不通过后更新信息
     */
    UPDATE("已更新信息");

    private final String str;

    CheckState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
