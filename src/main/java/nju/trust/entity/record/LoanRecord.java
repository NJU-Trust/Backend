package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
@Entity
public class LoanRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    private boolean success;

    private LocalDateTime successTime;

    public LoanRecord(User user, BaseTarget target) {
        super(user);
        this.target = target;
        success =false;
    }

    public void makeItSucceed() {
        success = true;
        successTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }


    public LocalDateTime getSuccessTime() {
        return successTime;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public void setTarget(BaseTarget target) {
        this.target = target;
    }
}
