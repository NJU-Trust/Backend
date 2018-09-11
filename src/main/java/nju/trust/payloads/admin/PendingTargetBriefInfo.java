package nju.trust.payloads.admin;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;

/**
 * @Author: 许杨
 * @Description: 管理员进行项目审核时列表中显示的待审核项目的简略信息
 * @Date: 2018/8/31
 */
public class PendingTargetBriefInfo {
    private String username;
    private CreditRating creditRating;
    private Long targetId;
    private TargetState targetState;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public TargetState getTargetState() {
        return targetState;
    }

    public void setTargetState(TargetState targetState) {
        this.targetState = targetState;
    }

    private PendingTargetBriefInfo(String username, CreditRating creditRating, Long targetId, TargetState targetState) {
        if(username == null) {
            this.username = "";
        }else {
            this.username = username;
        }
        this.creditRating = creditRating;
        this.targetId = targetId;
        this.targetState = targetState;
    }

    public PendingTargetBriefInfo(BaseTarget target) {
        String username = target.getUser().getUsername();
        CreditRating creditRating = target.getUser().getCreditRating();
        Long targetId = target.getId();
        TargetState targetState = target.getTargetState();

        if(username == null) {
            this.username = "";
        }else {
            this.username = username;
        }
        this.creditRating = creditRating;
        this.targetId = targetId;
        this.targetState = targetState;
    }
}
