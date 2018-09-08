package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 所在专业情况
 * @Date: 2018/9/8
 */
public class MajorRecord extends BaseUserEvidence{
    @Enumerated(value = EnumType.STRING)
    private MajorType majorType;

    public MajorRecord(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public MajorType getMajorType() {
        return majorType;
    }

    public void setMajorType(MajorType majorType) {
        this.majorType = majorType;
    }
}
