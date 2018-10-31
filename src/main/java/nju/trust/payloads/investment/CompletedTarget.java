package nju.trust.payloads.investment;

import nju.trust.entity.target.TargetState;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class CompletedTarget {

    private String projectName;

    private String loanFrom;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double investAmount;

    private Double interest;

    private TargetState state;

    private Double get;

    private Long targetId;

    public CompletedTarget(String projectName, String loanFrom, LocalDate startDate,
                           LocalDate endDate, Double investAmount, Double interest,
                           TargetState state, Double get, Long targetId) {
        this.projectName = projectName;
        this.loanFrom = loanFrom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.investAmount = investAmount;
        this.interest = interest;
        this.state = state;
        this.get = get;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getInvestAmount() {
        return investAmount;
    }

    public Double getInterest() {
        return interest;
    }

    public TargetState getState() {
        return state;
    }

    public Double getGet() {
        return get;
    }
}
