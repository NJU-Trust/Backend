package nju.trust.payloads.admin;

import nju.trust.entity.CheckState;
import nju.trust.entity.CreditRating;
import nju.trust.entity.target.*;
import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.RepaymentType;
import nju.trust.entity.user.User;

import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 待审核的标的详细信息
 * @Date: 2018/9/11
 */
public class PendingTargetDetailInfo {
    // 用户信息
    private String username;    // 用户名
    private CreditRating grade;    // 用户风险评级(信用等级)
    private String checkState;  // 用户审核状态

    // 项目基本详情
    private Long targetId;  // 项目编号
    private String name;    // 项目名称
    private LocalDate startTime;    // 项目开始时间
    private LocalDate endTime;  // 项目结束时间
    private String identityOption;  // 信息披露层级
    private Double completionRate;  // 最低达标率


    // 资金去向
    private String classify;    // 资金去向分类
    private String explanation; // 资金用途详情
    private String proof;    // 凭证

    // 贷款信息
    private double money;   // 贷款金额
    private double creditRating;    // 还款利率
    private Integer duration;   // 还款期数
    private String returntype;   // 还款方式

    public PendingTargetDetailInfo(User user, BaseTarget baseTarget, Repayment repayment, CheckState state, LargeProjectClassification classification) {
        username = user.getUsername();
        grade = user.getCreditRating();
        checkState = state.getStr();
        targetId = baseTarget.getId();
        name = baseTarget.getName();
        startTime = baseTarget.getStartTime();
        endTime = repayment.getStartDate().plusMonths(repayment.getDuration());
        identityOption = baseTarget.getIdentityOption().getStr();
        completionRate = baseTarget.getCompletionRate();
        classify = Classification.getClassification(classification).getStr();
        explanation = baseTarget.getUseOfFunds();
        proof = baseTarget.getProof();
        money = baseTarget.getMoney();
        creditRating = repayment.getInterestRate();
        duration = repayment.getDuration();
        returntype = repayment.getType().getStr();
    }

    public PendingTargetDetailInfo(User user, BaseTarget baseTarget, Repayment repayment, CheckState state, SmallProjectClassification classification) {
        username = user.getUsername();
        grade = user.getCreditRating();
        checkState = state.getStr();
        targetId = baseTarget.getId();
        name = baseTarget.getName();
        startTime = baseTarget.getStartTime();
        endTime = repayment.getStartDate().plusMonths(repayment.getDuration());
        identityOption = baseTarget.getIdentityOption().getStr();
        completionRate = baseTarget.getCompletionRate();
        classify = Classification.getClassification(classification).getStr();
        explanation = baseTarget.getUseOfFunds();
        proof = baseTarget.getProof();
        money = baseTarget.getMoney();
        creditRating = repayment.getInterestRate();
        duration = repayment.getDuration();
        returntype = repayment.getType().getStr();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreditRating getGrade() {
        return grade;
    }

    public void setGrade(CreditRating grade) {
        this.grade = grade;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckState checkState) {
        this.checkState = checkState.getStr();
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getIdentityOption() {
        return identityOption;
    }

    public void setIdentityOption(IdentityOption identityOption) {
        this.identityOption = identityOption.getStr();;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(Classification classify) {
        this.classify = classify.getStr();
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(double creditRating) {
        this.creditRating = creditRating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getReturntype() {
        return returntype;
    }

    public void setReturntype(RepaymentType returntype) {
        this.returntype = returntype.getStr();
    }
}
