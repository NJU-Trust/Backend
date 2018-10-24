package nju.trust.payloads.target;

import nju.trust.entity.target.BaseTarget;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public class OnGoingTarget {

    private String name;

    private LocalDate releaseDate;

    private Double interestRate;

    private Integer remainingRepaidPeriods;

    private Double remainingMoney;

    private LocalDate nextDueDate;

    private Double repayMoney;

    private Long targetId;

    public OnGoingTarget(String name, LocalDate releaseDate, Double interestRate,
                         Integer remainingRepaidPeriods, Double remainingMoney,
                         LocalDate nextDueDate, Double repayMoney, Long targetId) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.interestRate = interestRate;
        this.remainingRepaidPeriods = remainingRepaidPeriods;
        this.remainingMoney = remainingMoney;
        this.nextDueDate = nextDueDate;
        this.repayMoney = repayMoney;
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getRemainingRepaidPeriods() {
        return remainingRepaidPeriods;
    }

    public Double getRemainingMoney() {
        return remainingMoney;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public Double getRepayMoney() {
        return repayMoney;
    }
}
