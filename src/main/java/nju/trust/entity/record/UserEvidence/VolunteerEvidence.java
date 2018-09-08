package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 每年平均志愿活动时长
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("VOLUNTEERTIME")
public class VolunteerEvidence extends BaseUserEvidence{
    private double length;    // 年平均志愿活动时长

    public VolunteerEvidence(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public double getLength() {
        return length;
    }

    public void setTime(double length) {
        this.length = length;
    }
}
