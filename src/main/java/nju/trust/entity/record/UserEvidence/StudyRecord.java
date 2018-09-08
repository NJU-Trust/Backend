package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 学习成绩（前20%/50%/70%/90%/其他）
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("STUDY")
public class StudyRecord extends BaseUserEvidence{
    private double ranking;

    public StudyRecord(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }
}
