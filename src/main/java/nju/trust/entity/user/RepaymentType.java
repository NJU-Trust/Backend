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
    EQUAL_PRINCIPAL("等额本金"),
    /**
     * 等额本息
     */
    EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST("等额本息"),
    /**
     * 一次性还付本息
     */
    ONE_TIME_PAYMENT("一次性还付本息"),
    /**
     * 先息后本
     */
    PRE_INTEREST("先息后本");

    private final String str;

    RepaymentType(String str) {
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }

    public static RepaymentType getEnum(String str) {
        RepaymentType[] repaymentTypes = RepaymentType.values();
        for(RepaymentType repaymentType : repaymentTypes) {
            if(str.equals(repaymentType.getStr())) {
                return repaymentType;
            }
        }
        return null;
    }
}
