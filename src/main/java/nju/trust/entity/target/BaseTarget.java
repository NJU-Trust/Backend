package nju.trust.entity.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,
            mappedBy = "target")
    private Repayment repayment;

    IdentityOption identityOption;

    /**
     * 资金用途
     */
    private String useOfFunds;

    /**
     * 凭证
     */
    private String proof;

    public BaseTarget() {
    }

    public BaseTarget(LocalDate startTime, String name, Double money, String useOfFunds,
                      Double completionRate, String projectDescription, User user, String proof) {
        this.startTime = startTime;
        this.name = name;
        this.money = money;
        this.completionRate = completionRate;
        this.projectDescription = projectDescription;
        this.user = user;
        this.useOfFunds = useOfFunds;
        this.proof = proof;

        targetState = TargetState.PENDING;
        collectedMoney = 0.;

    }

    public boolean inThePayment() {
        return targetState == TargetState.IN_THE_PAYMENT;
    }

    public boolean tryToSetToInThePayment() {
        if (collectedMoney.equals(money)) {
            targetState = TargetState.IN_THE_PAYMENT;
            return true;
        }

        return false;
    }

    public double currentProgress() {
        return collectedMoney / money;
    }

    public double needMoney() {
        return money - collectedMoney;
    }

    public void setRepaymentStartDate(LocalDate date) {
        repayment.setStartDate(date);
    }

    public Repayment getRepayment() {
        return repayment;
    }

    public String getUseOfFunds() {
        return useOfFunds;
    }

    public int getRepaymentDuration() {
        return repayment.getDuration();
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

    public String getProof() {
        return proof;
    }
}
