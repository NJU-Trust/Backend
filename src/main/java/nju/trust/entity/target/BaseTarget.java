package nju.trust.entity.target;

import nju.trust.entity.IdentityOption;
import nju.trust.entity.TargetRating;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;
import nju.trust.entity.user.User;
import nju.trust.payloads.target.BasicTargetRequest;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "targetType")
public abstract class BaseTarget {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "username", nullable = false)
    private User user;

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
     * 项目利率，同时也是收益情况，百分数
     */
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double interestRate;

    /**
     * 平台项目评级
     */
    @Enumerated(value = EnumType.STRING)
    private TargetRating targetRating;

    /**
     * 项目风险评级分数（同时也是计算所得的标的成功率）
     */
    private Double targetRatingScore;

    @Enumerated(EnumType.STRING)
    @NotNull TargetType targetType;

    @Lob
    private String projectDescription;

    @Lob
    @ElementCollection
    @Basic(fetch = FetchType.LAZY)
    private List<byte[]> files;

    /**
     * 还款期限
     * todo 考虑从哪里赋值
     */
    private Integer repaymentDuration;

    IdentityOption identityOption;

    BaseTarget(BasicTargetRequest request, User user) {
        this.user = user;
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

    public Double getTargetRatingScore() {
        return targetRatingScore;
    }

    public void setTargetRatingScore(Double targetRatingScore) {
        this.targetRatingScore = targetRatingScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRepaymentDuration() {
        return repaymentDuration;
    }

    public void setRepaymentDuration(Integer repaymentDuration) {
        this.repaymentDuration = repaymentDuration;
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
