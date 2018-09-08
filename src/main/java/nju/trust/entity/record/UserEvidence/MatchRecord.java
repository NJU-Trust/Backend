package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

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
public class MatchRecord extends BaseUserEvidence {
    @Enumerated(value = EnumType.STRING)
    private BonusPunishmentType type;

    public MatchRecord(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public BonusPunishmentType getType() {
        return type;
    }

    public void setType(BonusPunishmentType type) {
        this.type = type;
    }
}
