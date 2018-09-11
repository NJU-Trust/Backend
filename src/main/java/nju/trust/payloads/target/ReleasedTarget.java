package nju.trust.payloads.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.user.Repayment;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public class ReleasedTarget {

    private String name;

    private Double money;

    private Double interestRate;

    private LocalDate[] duration;

    private TargetState state;

    private Long targetId;

    public ReleasedTarget(String name, Double money, Double interestRate,
                          LocalDate[] duration, TargetState state, Long targetId) {
        this.name = name;
        this.money = money;
        this.interestRate = interestRate;
        this.duration = duration;
        this.state = state;
        this.targetId = targetId;
    }

    public ReleasedTarget(BaseTarget target) {
        name = target.getName();
        money = target.getMoney();
        Repayment repayment = target.getRepayment();
        interestRate = repayment.getInterestRate();
        duration = new LocalDate[]{target.getStartTime(),
                repayment.getStartDate().plusMonths(repayment.getDuration())};
        state = target.getTargetState();
        targetId = target.getId();
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return money;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public LocalDate[] getDuration() {
        return duration;
    }

    public TargetState getState() {
        return state;
    }

    public Long getTargetId() {
        return targetId;
    }
}
