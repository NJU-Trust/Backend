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
 * @Description: 身份信息验证
 * @Date: 2018/10/27
 */
@Entity
@DiscriminatorValue("SELFINFO")
public class SelfInfoEvidence extends BaseUserEvidence{
    @Enumerated(value = EnumType.STRING)
    private SelfInfoEvidenceType evidenceType;    // 姓名

    @Enumerated(value = EnumType.STRING)
    private SelfInfoType type;      // 类别：学生、校友

    public SelfInfoEvidence(){}
    public SelfInfoEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }
    public SelfInfoEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence, SelfInfoEvidenceType evidenceType, SelfInfoType type) {
        super(user, item, time, state, evidence);
        this.evidenceType = evidenceType;
        this.type = type;
    }

    public SelfInfoEvidenceType getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(SelfInfoEvidenceType evidenceType) {
        this.evidenceType = evidenceType;
    }

    public SelfInfoType getType() {
        return type;
    }

    public void setType(SelfInfoType type) {
        this.type = type;
    }
}
