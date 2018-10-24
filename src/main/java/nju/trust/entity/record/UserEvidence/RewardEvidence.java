package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 奖励
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("REWARD")
public class RewardEvidence extends BaseUserEvidence {
    @Enumerated(value = EnumType.STRING)
    private RewardType type;

    public RewardEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence, RewardType type) {
        super(user, item, time, state, evidence);
        this.type = type;
    }

    public RewardEvidence() {
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }
}
