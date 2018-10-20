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
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "targetId")
    private BaseTarget target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    private Double interestRate;

    private Integer duration;

    /**
     * 总利息
     */
    private Double totalInterest;

    /**
     * 还款开始时间，同时也是借款募集截止时间。
     * 如果在这个时间之前就满标，则将改时间改为
     * 满标时的时间，并从此时开始计算利息
     */
    private LocalDate startDate;

    /**
     * 剩余还款金额
     */
    private Double remainingAmount;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double difficulty;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private RepaymentType type;

    public Repayment() {
    }

    public Repayment(BaseTarget target, User user, Double interestRate, Double totalInterest,
                     Integer duration, LocalDate startDate, Double remainingAmount,
                     @DecimalMin("0.0") @DecimalMax("100.0") Double difficulty, RepaymentType type) {
        this.target = target;
        this.user = user;
        this.interestRate = interestRate;
        this.duration = duration;
        this.startDate = startDate;
        this.remainingAmount = remainingAmount;
        this.difficulty = difficulty;
        this.type = type;
        this.totalInterest = totalInterest;
    }

    /**
     * 距离最近的一次还款时间还有多少天
     * @return 距离最近的一次还款时间的天数，如果
     * 还未开始还款，则返回-1
     */
    public long nextDue() {
//        if (LocalDate.now().isBefore(startDate))
//            return -1L;

        LocalDate now = LocalDate.now();
        LocalDate pointer = startDate;
        while (pointer.isBefore(now)) {
            pointer = pointer.plusMonths(1);
        }
        return now.until(pointer, ChronoUnit.DAYS);
    }

    public LocalDate nextDueDate() {
        LocalDate now = LocalDate.now();
        LocalDate pointer = startDate;
        while (pointer.isBefore(now)) {
            pointer = pointer.plusMonths(1);
        }
        return pointer;
    }

    public Double getTotalInterest() {
        return totalInterest;
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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }
}
