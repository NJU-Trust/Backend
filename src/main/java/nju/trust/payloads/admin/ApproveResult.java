package nju.trust.payloads.admin;

import nju.trust.entity.target.TargetState;

/**
 * @Author: 许杨
 * @Description: 管理员审批项目的审批结果
 * @Date: 2018/8/31
 */
public enum ApproveResult {
    PASS("审批通过", TargetState.ON_GOING),
    HARMFULINFORMATION("含有恶意信息，审批不通过", TargetState.HARMFUL),
    LACKINFO("信息缺失，审批不通过", TargetState.LACKINFO),
    INFORMAL("信息不规范，审批不通过", TargetState.INFROMAL);

    private String str;

    private TargetState state;  // 对应的项目的状态

    ApproveResult(String str, TargetState state) {
        this.str = str;
        this.state = state;
    }

    ApproveResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public TargetState getState() {
        return state;
    }
}
