package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 图书馆借阅还书情况
 * @Date: 2018/9/8
 */
@Entity
@DiscriminatorValue("RETURNBOOKS")
public class ReturnBooksEvidence extends BaseUserEvidence{
    private Integer num;

    public ReturnBooksEvidence(User user, UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(user, item, time, state, evidence);
    }

    public ReturnBooksEvidence() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
