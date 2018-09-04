package nju.trust.entity.user;

import nju.trust.entity.target.BaseTarget;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Author: J.D. Liao
 * Date: 2018/8/29
 * Description:
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    private RepaymentType type;

    /**
     * 获得本月还款金额
     * @return 本月应还金额
     */
    public abstract double getThisMonthAmount();

    /**
     * 距离最近的一次还款时间还有多少天
     * @return 距离最近的一次还款时间的天数，如果
     * 还未开始还款，则返回-1
     */
    public long nextDue() {
        if (LocalDate.now().isBefore(startDate))
            return -1L;

        LocalDate now = LocalDate.now();
        LocalDate pointer = startDate;
        while (pointer.isBefore(now)) {
            pointer = pointer.plusMonths(1);
        }
        return now.until(pointer, ChronoUnit.DAYS);
    }

    public RepaymentType getType() {
        return type;
    }

    public void setType(RepaymentType type) {
        this.type = type;
    }

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
