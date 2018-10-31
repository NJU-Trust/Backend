package nju.trust.payloads.investment;

import nju.trust.entity.target.TargetState;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class InvestmentTarget {

    private String projectName;

    private String loanFrom;

    private LocalDate loanDate;

    private Double investAmount;

    private Integer num;

    private Double interest;

    private Double repaymentAmountMonth;

    private LocalDate deadlineDate;

    private Double deadlineAmount;

    private TargetState state;

    private Long targetId;

    public InvestmentTarget(String projectName, String loanFrom, LocalDate loanDate,
                            Double investAmount, Integer num, Double interest,
                            Double repaymentAmountMonth, LocalDate deadlineDate,
                            Double deadlineAmount, TargetState state, Long targetId) {
        this.projectName = projectName;
        this.loanFrom = loanFrom;
        this.loanDate = loanDate;
        this.investAmount = investAmount;
        this.num = num;
        this.interest = interest;
        this.repaymentAmountMonth = repaymentAmountMonth;
        this.deadlineDate = deadlineDate;
        this.deadlineAmount = deadlineAmount;
        this.state = state;
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getLoanFrom() {
        return loanFrom;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public Double getInvestAmount() {
        return investAmount;
    }

    public Integer getNum() {
        return num;
    }

    public Double getInterest() {
        return interest;
    }

    public Double getRepaymentAmountMonth() {
        return repaymentAmountMonth;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public Double getDeadlineAmount() {
        return deadlineAmount;
    }

    public TargetState getState() {
        return state;
    }
}
