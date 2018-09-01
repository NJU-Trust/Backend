package nju.trust.entity;

/**
 * Author: J.D. Liao
 * Date: 2018/8/9
 */
public enum CheckState {

    /**
     * Check is on going
     */
    ONGING,

    /**
     * The application has been approved
     */
    PASS,

    /**
     * The application was rejected
     */
    REJECT,

    /**
     * 用户在不通过后更新信息
     */
    UPDATE
}
