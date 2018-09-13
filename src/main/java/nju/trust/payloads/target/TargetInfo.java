package nju.trust.payloads.target;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import nju.trust.entity.target.TargetRating;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class TargetInfo {

    @NotNull
    private Long id;

    @JsonIgnore
    private TargetType targetType;

    private String username;

    private LocalDate startTime;

    /**
     * Target name
     */
    private String name;

    private Double money;

    private Double collectedMoney;

    private TargetState state;

    /**
     * 完成度限制
     */
    @JsonProperty("completionRate")
    private Double rate;

    /**
     * 利率（同时也是项目收益情况）
     */
    private Double interestRate;

    /**
     * 项目风险评级
     */
    @JsonProperty("riskRating")
    private TargetRating targetRating;

    private String projectDescription;

    @JsonProperty("useOfFunds")
    private String useOfFunds;

    public TargetInfo(BaseTarget baseTarget) {
        id = baseTarget.getId();
        targetType = baseTarget.getTargetType();
        startTime = baseTarget.getStartTime();
        name = baseTarget.getName();
        money = baseTarget.getMoney();
        collectedMoney = baseTarget.getCollectedMoney();
        state = baseTarget.getTargetState();
        rate = baseTarget.getCompletionRate();
        interestRate = baseTarget.getRepayment().getInterestRate();
        targetRating = baseTarget.getTargetRating();
        projectDescription = baseTarget.getProjectDescription();
        User user = baseTarget.getUser();
        username = user.getUsername();
        useOfFunds = baseTarget.getUseOfFunds();
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "id=" + id +
                ", targetType=" + targetType +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", state=" + state +
                ", rate=" + rate +
                ", interestRate=" + interestRate +
                ", targetRating=" + targetRating +
                ", projectDescription='" + projectDescription + '\'' +
                ", useOfFunds='" + useOfFunds + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
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

}
