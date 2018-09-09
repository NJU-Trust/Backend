package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 是否为失信人员
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("DISCREDIT")
public class DiscreditEvidence extends BaseUserEvidence{
    private Integer num;    // 次数

    /*public DiscreditEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }*/

    public DiscreditEvidence(){}

    public DiscreditEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public DiscreditEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence, int num) {
        super(user, item, time, state, evidence);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
