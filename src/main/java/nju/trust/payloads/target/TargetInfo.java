package nju.trust.payloads.target;

import nju.trust.entity.TargetRating;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;
import nju.trust.entity.target.BaseTarget;

import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class TargetInfo {

    private TargetType targetType;

    private String username;

    private LocalDateTime startTime;

    /**
     * Target name
     */
    private String name;

    private Double money;

    private Double collectedMoney;

    private Double grantedMoney;

    private TargetState state;

    /**
     * Limit of completion rate
     */
    private Double rate;

    private String riskAnalysis;

    private String consumptionAdvise;

    private Double interestRate;

    private TargetRating targetRating;

    private String projectDescription;

    public TargetInfo(BaseTarget baseTarget) {
        targetType = baseTarget.getTargetType();
        username = baseTarget.getUsername();
        startTime = baseTarget.getStartTime();
        name = baseTarget.getName();
        money = baseTarget.getMoney();
        collectedMoney = baseTarget.getCollectedMoney();
        grantedMoney = baseTarget.getGrantedMoney();
        state = baseTarget.getTargetState();
        rate = baseTarget.getCompletionRate();
        riskAnalysis = baseTarget.getRiskAnalysis();
        consumptionAdvise = baseTarget.getConsumptionAdvise();
        interestRate = baseTarget.getInterestRate();
        targetRating = baseTarget.getTargetRating();
        projectDescription = baseTarget.getProjectDescription();
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "targetType=" + targetType +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", grantedMoney=" + grantedMoney +
                ", state=" + state +
                ", rate=" + rate +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                ", consumptionAdvise='" + consumptionAdvise + '\'' +
                ", interestRate=" + interestRate +
                ", targetRating=" + targetRating +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public TargetRating getTargetRating() {
        return targetRating;
    }

    public void setTargetRating(TargetRating targetRating) {
        this.targetRating = targetRating;
    }

    public String getConsumptionAdvise() {
        return consumptionAdvise;
    }

    public void setConsumptionAdvise(String consumptionAdvise) {
        this.consumptionAdvise = consumptionAdvise;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getCollectedMoney() {
        return collectedMoney;
    }

    public void setCollectedMoney(Double collectedMoney) {
        this.collectedMoney = collectedMoney;
    }

    public Double getGrantedMoney() {
        return grantedMoney;
    }

    public void setGrantedMoney(Double grantedMoney) {
        this.grantedMoney = grantedMoney;
    }

    public TargetState getState() {
        return state;
    }

    public void setState(TargetState state) {
        this.state = state;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    public void setRiskAnalysis(String riskAnalysis) {
        this.riskAnalysis = riskAnalysis;
    }
}
