package nju.trust.entity.record;

import com.google.common.collect.ImmutableMap;
import nju.trust.entity.CheckState;
import nju.trust.entity.target.TargetState;

import java.util.Map;

/**
 * @Author: 许杨
 * @Description: 管理员审批项目的审批结果
 * @Date: 2018/8/31
 */
public enum ApproveResult {
    ONGOING("等待审批", TargetState.PENDING, CheckState.ONGING),
    PASS("通过", TargetState.ON_GOING, CheckState.PASS),
    HARMFULINFORMATION("含有恶意信息，审批不通过", TargetState.HARMFUL, CheckState.REJECT),
    LACKINFO("信息缺失，审批不通过", TargetState.LACK_INFO, CheckState.REJECT),
    INFORMAL("信息不规范，审批不通过", TargetState.INFORMAL, CheckState.REJECT);

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

    public Map<String, Object> toMap() {
        return ImmutableMap.<String, Object>builder()
                .put("str", str)
                .build();
    }

    public static ApproveResult getEnum(String str) {
        ApproveResult[] approveResults = ApproveResult.values();
        for(ApproveResult approveResult : approveResults) {
            if(approveResult.getStr().equals(str)) {
                return approveResult;
            }
        }
        return ApproveResult.PASS;
    }
}
