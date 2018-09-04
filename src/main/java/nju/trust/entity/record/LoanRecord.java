package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
public class LoanRecord extends BaseRecord {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    public LoanRecord(User user, BaseTarget target) {
        super(user);
        this.target = target;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public void setTarget(BaseTarget target) {
        this.target = target;
    }
}
