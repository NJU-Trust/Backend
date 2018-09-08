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
 * @Description: 学生工作
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("STUDENTWORK")
public class StudentWorkRecord extends BaseUserEvidence{
    @Enumerated(value = EnumType.STRING)
    private StudentWorkType type;


    public StudentWorkRecord(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public StudentWorkType getType() {
        return type;
    }

    public void setType(StudentWorkType type) {
        this.type = type;
    }
}