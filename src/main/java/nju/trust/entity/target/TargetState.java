package nju.trust.entity.target;

public enum TargetState {
    /**
     * 招标中
     */
    ON_GOING,
    /**
     * 审核中
     */
    PENDING,
    /**
     * 还款中
     */
    IN_THE_PAYMENT,
    /**
     * 已还款
     */
    PAY_OFF,
    /**
     * 含有恶意信息，审批不通过
     */
    HARMFUL,
    /**
     * 信息缺失，审批不通过
     */
    LACKINFO,
    /**
     * 信息不规范，审批不通过
     */
    INFROMAL
}
