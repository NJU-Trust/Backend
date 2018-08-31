package nju.trust.entity.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public enum RepaymentType {
    /**
     * 等额本金
     */
    EQUAL_PRINCIPAL,
    /**
     * 等额本息
     */
    EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST,
    /**
     * 一次性还付本息
     */
    ONE_TIME_PAYMENT,
    /**
     * 先息后本
     */
    PRE_INTEREST
}
