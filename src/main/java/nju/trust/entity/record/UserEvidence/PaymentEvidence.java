package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 学费及住宿费的缴纳情况
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("PAYMENT")
public class PaymentEvidence extends BaseUserEvidence {
    private Integer num;
    public PaymentEvidence(){}
    public PaymentEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
