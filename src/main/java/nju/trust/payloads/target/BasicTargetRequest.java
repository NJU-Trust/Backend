package nju.trust.payloads.target;

import nju.trust.entity.user.RepaymentType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
public class BasicTargetRequest {

    private LocalDate startTime;

    private Double money;

    private String name;

    private String projectDescription;

    private Double completionRate;

    private RepaymentType repaymentType;

    private Double interestRate;

    @NotNull
    private Integer duration;

    private String useOfFunds;

    private String proof;


    @Override
    public String toString() {
        return "BasicTargetRequest{" +
                "startTime=" + startTime +
                ", money=" + money +
                ", name='" + name + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", completionRate=" + completionRate +
                ", repaymentType=" + repaymentType +
                ", interestRate=" + interestRate +
                ", duration=" + duration +
                '}';
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getProof() {
        return proof;
    }

    public String getUseOfFunds() {
        return useOfFunds;
    }

    public void setUseOfFunds(String useOfFunds) {
        this.useOfFunds = useOfFunds;
    }

    public RepaymentType getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(RepaymentType repaymentType) {
        this.repaymentType = repaymentType;
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

}
