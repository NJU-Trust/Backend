package nju.trust.entity.target;

import nju.trust.entity.IdentityOption;
import nju.trust.entity.TargetRating;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@MappedSuperclass
@DiscriminatorColumn(name = "target_type")
public class BaseTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private LocalDateTime startTime;

    private String name;

    private Double money;

    private Double collectedMoney;

    private Double grantedMoney;

    @Enumerated(value = EnumType.STRING)
    private TargetState targetState;

    /**
     * 完成度限制
     */
    private Double completionRate;

    /**
     * 收益情况
     */
    private String incomeSituation;

    private String riskAnalysis;

    /**
     * 消费修正建议
     */
    private String consumptionAdvise;

    /**
     * 项目利率
     */
    private Double interestRate;

    @Enumerated(value = EnumType.STRING)
    private TargetRating targetRating;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TargetType targetType;

    private String projectDescription;

    @ElementCollection(targetClass = String.class)
    private List<String> files;

    private IdentityOption identityOption;

    @Override
    public String toString() {
        return "BaseTarget{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", grantedMoney=" + grantedMoney +
                ", targetState=" + targetState +
                ", completionRate=" + completionRate +
                ", incomeSituation='" + incomeSituation + '\'' +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                ", consumptionAdvise='" + consumptionAdvise + '\'' +
                ", interestRate=" + interestRate +
                ", targetRating=" + targetRating +
                ", targetType=" + targetType +
                ", identityOption=" + identityOption +
                ", projectDescription=" + projectDescription +
                ", files=" + files +
                '}';
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
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

    public String getConsumptionAdvise() {
        return consumptionAdvise;
    }

    public void setConsumptionAdvise(String consumptionAdvise) {
        this.consumptionAdvise = consumptionAdvise;
    }

    public IdentityOption getIdentityOption() {
        return identityOption;
    }

    public void setIdentityOption(IdentityOption identityOption) {
        this.identityOption = identityOption;
    }

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

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
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

}
