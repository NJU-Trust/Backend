package nju.trust.entity.record;

import nju.trust.entity.CheckState;
import nju.trust.entity.target.TargetState;

/**
 * @Author: 许杨
 * @Description: 管理员审批项目的审批结果
 * @Date: 2018/8/31
 */
public enum ApproveResult {
    ONGOING("等待审批", TargetState.PENDING, CheckState.ONGING),
    PASS("审批通过", TargetState.ON_GOING, CheckState.PASS),
    HARMFULINFORMATION("含有恶意信息，审批不通过", TargetState.HARMFUL, CheckState.REJECT),
    LACKINFO("信息缺失，审批不通过", TargetState.LACKINFO, CheckState.REJECT),
    INFORMAL("信息不规范，审批不通过", TargetState.INFROMAL, CheckState.REJECT);

    private String str;

    private TargetState targetState;  // 对应的项目的状态

    private CheckState checkState;

    ApproveResult(String str, TargetState targetState, CheckState checkState) {
        this.str = str;
        this.targetState = targetState;
        this.checkState = checkState;
    }

    ApproveResult(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public TargetState getTargetState() {
        return targetState;
    }

    public CheckState getCheckState() {
        return checkState;
    }
}
