package nju.trust.payloads.target;

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

    private LocalDateTime endTime;

    private LocalDateTime repaymentTime;

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

    /**
     * Current completion rate
     */
    private Double progress;

    private String incomeSituation;

    private String riskAnalysis;

    public TargetInfo(BaseTarget baseTarget) {
        targetType = baseTarget.getTargetType();
        username = baseTarget.getUsername();
        startTime = baseTarget.getStartTime();
        endTime = baseTarget.getEndTime();
        repaymentTime = baseTarget.getRepaymentTime();
        name = baseTarget.getName();
        money = baseTarget.getMoney();
        collectedMoney = baseTarget.getCollectedMoney();
        grantedMoney = baseTarget.getGrantedMoney();
        state = baseTarget.getTargetState();
        rate = baseTarget.getRate();
        progress = baseTarget.getProgress();
        incomeSituation = baseTarget.getIncomeSituation();
        riskAnalysis = baseTarget.getRiskAnalysis();
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "targetType=" + targetType +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", repaymentTime=" + repaymentTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", grantedMoney=" + grantedMoney +
                ", state=" + state +
                ", rate=" + rate +
                ", progress=" + progress +
                ", incomeSituation='" + incomeSituation + '\'' +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                '}';
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(LocalDateTime repaymentTime) {
        this.repaymentTime = repaymentTime;
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

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public String getIncomeSituation() {
        return incomeSituation;
    }

    public void setIncomeSituation(String incomeSituation) {
        this.incomeSituation = incomeSituation;
    }

    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    public void setRiskAnalysis(String riskAnalysis) {
        this.riskAnalysis = riskAnalysis;
    }
}