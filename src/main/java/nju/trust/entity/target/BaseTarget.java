package nju.trust.entity.target;

import nju.trust.entity.IdentityOption;
import nju.trust.entity.TargetRating;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;
import nju.trust.payloads.target.BasicTargetRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@DiscriminatorColumn(name = "target_type")
public class BaseTarget {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private LocalDateTime startTime;

    private String name;

    private Double money;

    private Double collectedMoney;

    @Enumerated(value = EnumType.STRING)
    private TargetState targetState;

    /**
     * 完成度限制
     */
    private Double completionRate;

    private String riskAnalysis;

    /**
     * 消费修正建议
     */
    private String consumptionAdvise;

    /**
     * 项目利率，同时也是收益情况
     */
    private Double interestRate;

    /**
     * 平台项目评级
     */
    @Enumerated(value = EnumType.STRING)
    private TargetRating targetRating;

    @Enumerated(EnumType.STRING)
    @NotNull TargetType targetType;

    private String projectDescription;

    @Lob
    @ElementCollection
    @Basic(fetch = FetchType.LAZY)
    private List<byte[]> files;

    IdentityOption identityOption;

    BaseTarget(BasicTargetRequest request, String username) {
        this.username = username;
        startTime = request.getStartTime();
        name = request.getName();
        money = request.getMoney();
        completionRate = request.getCompletionRate();
        projectDescription = request.getProjectDescription();

        collectedMoney = 0.;
        targetState = TargetState.PENDING;
    }

    @Override
    public String toString() {
        return "BaseTarget{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", startTime=" + startTime +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", collectedMoney=" + collectedMoney +
                ", targetState=" + targetState +
                ", completionRate=" + completionRate +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                ", consumptionAdvise='" + consumptionAdvise + '\'' +
                ", interestRate=" + interestRate +
                ", targetRating=" + targetRating +
                ", targetType=" + targetType +
                ", identityOption=" + identityOption +
                ", projectDescription=" + projectDescription +
                '}';
    }

    public List<byte[]> getFiles() {
        return files;
    }

    public void setFiles(List<byte[]> files) {
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
