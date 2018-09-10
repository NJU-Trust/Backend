/*
package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

*/
/**
 * @Author: 许杨
 * @Description: 违纪或治安处罚等不良信息
 * @Date: 2018/9/8
 *//*

@Entity
@DiscriminatorValue("PUNISHMENT")
public class PunishmentEvidence extends BaseUserEvidence{
    @Enumerated(value = EnumType.STRING)
    private BonusType type;

    public PunishmentEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public PunishmentEvidence() {
    }

    public BonusType getType() {
        return type;
    }

    public void setType(BonusType type) {
        this.type = type;
    }
}
*/
