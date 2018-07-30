package nju.trust.entity.target;

import nju.trust.entity.IdentityOption;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
@DiscriminatorColumn(name = "target_type")
public class BaseTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime repaymentTime;

    private String name;

    private Double money;

    private Double collectedMoney;

    private Double grantedMoney;

    @Enumerated(value = EnumType.STRING)
    private TargetState targetState;

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

    @Enumerated(EnumType.STRING)
    @NotNull
    private TargetType targetType;

    private IdentityOption identityOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TargetState getTargetState() {
        return targetState;
    }

    public void setTargetState(TargetState targetState) {
        this.targetState = targetState;
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

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    @Override
    public String toString() {
        return "BaseTarget{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", repaymentTime=" + repaymentTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", grantedMoney=" + grantedMoney +
                ", targetState=" + targetState +
                ", rate=" + rate +
                ", progress=" + progress +
                ", incomeSituation='" + incomeSituation + '\'' +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                ", targetType=" + targetType +
                ", identityOption=" + identityOption +
                '}';
    }
}
