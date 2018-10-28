package nju.trust.entity.target;

public enum TargetState {
    /**
     * 招标中
     */
    ON_GOING("招标中"),
    /**
     * 审核中
     */
    PENDING("审核中"),
    /**
     * 还款中
     */
    IN_THE_PAYMENT("还款中"),
    /**
     * 已还款
     */
    PAY_OFF("已还款"),
    /**
     * 含有恶意信息，审批不通过
     */
    HARMFUL("含有恶意信息，审批不通过"),
    /**
     * 信息缺失，审批不通过
     */
    LACK_INFO("信息缺失，审批不通过"),
    /**
     * 信息不规范，审批不通过
     */
    INFORMAL("信息不规范，审批不通过");

    private String str;

    TargetState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static TargetState getEnum(String str) {
        TargetState[] targetStates = TargetState.values();

        for(TargetState state : targetStates) {
            if(str.equals(state.getStr())) {
                return state;
            }
        }
        return null;
    }
}
