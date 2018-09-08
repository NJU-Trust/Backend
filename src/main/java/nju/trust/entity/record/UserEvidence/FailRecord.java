package nju.trust.entity.record.UserEvidence;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 挂科数
 * @Date: 2018/9/8
 */
public class FailRecord extends BaseUserEvidence{
    private int num;

    public FailRecord(UserInfoCheckRecord item, LocalDateTime time, CheckState state, String evidence) {
        super(item, time, state, evidence);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
