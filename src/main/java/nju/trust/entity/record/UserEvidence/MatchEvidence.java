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
 * @Description: 科研竞赛获奖情况
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("MATCH")
public class MatchEvidence extends BaseUserEvidence {
    @Enumerated(value = EnumType.STRING)
    private BonusType type;

    public MatchEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public MatchEvidence() {
    }

    public BonusType getType() {
        return type;
    }

    public void setType(BonusType type) {
        this.type = type;
    }
}
