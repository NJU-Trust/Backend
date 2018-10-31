package nju.trust.payloads.investment;

import nju.trust.entity.target.TargetState;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class CollectedTarget {

    private String projectName;

    private String loanFrom;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double investAmount;

    private Integer num;

    private Double interest;

    private TargetState state;

    private Double trust;

    private Long targetId;

    public CollectedTarget(String projectName, String loanFrom, LocalDate startDate,
                           LocalDate endDate, Double investAmount, Integer num,
                           Double interest, TargetState state, Double trust, Long targetId) {
        this.projectName = projectName;
        this.loanFrom = loanFrom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.investAmount = investAmount;
        this.num = num;
        this.interest = interest;
        this.state = state;
        this.trust = trust;
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

    public Integer getNum() {
        return num;
    }

    public Double getInterest() {
        return interest;
    }

    public TargetState getState() {
        return state;
    }

    public Double getTrust() {
        return trust;
    }
}
