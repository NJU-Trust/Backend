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
 * @Description: 学校分类
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("SCHOOLTYPE")
public class SchoolEvidence extends BaseUserEvidence {
    @Enumerated(value = EnumType.STRING)
    private SchoolType schoolType;

    public SchoolEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public SchoolEvidence() {
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }
}
