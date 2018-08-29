package nju.trust.entity.user;

import nju.trust.entity.target.BaseTarget;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/8/29
 * Description:
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class Repayment {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private BaseTarget target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    private Double monthlyRepaymentAmount;

    private Double yearInterestRate;

    private Integer repaymentDuration;

    /**
     * 还款开始时间
     */
    private LocalDate startDate;

    /**
     * 剩余还款金额
     */
    private Double remainingAmount;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double difficultyStar;

    /**
     * 获得本月还款金额
     * @return 本月应还金额
     */
    public abstract double getThisMonthAmount();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public void setTarget(BaseTarget target) {
        this.target = target;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getMonthlyRepaymentAmount() {
        return monthlyRepaymentAmount;
    }

    public void setMonthlyRepaymentAmount(Double monthlyRepaymentAmount) {
        this.monthlyRepaymentAmount = monthlyRepaymentAmount;
    }

    public Double getYearInterestRate() {
        return yearInterestRate;
    }

    public void setYearInterestRate(Double yearInterestRate) {
        this.yearInterestRate = yearInterestRate;
    }

    public Integer getRepaymentDuration() {
        return repaymentDuration;
    }

    public void setRepaymentDuration(Integer repaymentDuration) {
        this.repaymentDuration = repaymentDuration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Double getDifficultyStar() {
        return difficultyStar;
    }

    public void setDifficultyStar(Double difficultyStar) {
        this.difficultyStar = difficultyStar;
    }
}
