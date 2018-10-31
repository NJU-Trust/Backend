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
    ONGOING("等待审批", TargetState.PENDING, CheckState.ONGING, "ONGOING"),
    PASS("通过", TargetState.ON_GOING, CheckState.PASS, "PASS"),
    HARMFULINFORMATION("含有恶意信息，审批不通过", TargetState.HARMFUL, CheckState.REJECT, "HARMFULINFORMATION"),
    LACKINFO("信息缺失，审批不通过", TargetState.LACK_INFO, CheckState.REJECT, "LACKINFO"),
    INFORMAL("信息不规范，审批不通过", TargetState.INFORMAL, CheckState.REJECT, "INFORMAL");

    private final String str;
    private final TargetState targetState;  // 对应的项目的状态
    private final CheckState checkState;
    private final String name;

    ApproveResult(String str, TargetState targetState, CheckState checkState, String name) {
        this.str = str;
        this.targetState = targetState;
        this.checkState = checkState;
        this.name = name;
    }

    public String getStr() {
        return str;
    }

    public String getName() {
        return name;
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
            if(approveResult.getName().equals(str)) {
                return approveResult;
            }
        }
        return ApproveResult.PASS;
    }
}
