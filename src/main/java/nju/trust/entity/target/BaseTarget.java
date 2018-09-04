package nju.trust.entity.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    /**
     * 开始进行募集的时间
     */
    private LocalDate startTime;

    private String name;

    private Double money;

    private Double collectedMoney;

    @Enumerated(value = EnumType.STRING)
    private TargetState targetState;

    /**
     * 完成度限制
     */
    private Double completionRate;

    /**
     * 平台项目评级（风险评级）
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

    /**
     * 还款方案
     */
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Repayment repayment;

    IdentityOption identityOption;

    public BaseTarget(LocalDate startTime, String name, Double money,
                      Double completionRate, String projectDescription, User user) {
        this.startTime = startTime;
        this.name = name;
        this.money = money;
        this.completionRate = completionRate;
        this.projectDescription = projectDescription;
        this.user = user;

        targetState = TargetState.PENDING;
        collectedMoney = 0.;
    }

    public Repayment getRepayment() {
        return repayment;
    }

    public void setRepayment(Repayment repayment) {
        this.repayment = repayment;
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

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

}
