package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.UserLevel;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetRating;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.RepaymentType;

/**
 * Author: J.D. Liao
 * Date: 2018/9/15
 * Description:
 */
public class TargetDetails {

    private Double progress;

    private Long leftDays;

    private Double leftNeeds;

    private Integer lifeOfLoan;

    private Double totalLoan;

    private String picPath;

    private RepaymentType payWay;

    private String useWay;

    private Double monthInterest;

    /**
     * 到期需换本金
     */
    private Double payAll;

    private String PS;

    private CreditRating userLevel;


    private TargetRating projectLevel;

    private String username;

    public TargetDetails(BaseTarget target, RepaymentRecord repaymentRecord) {
        username = target.getUser().getUsername();
        progress = target.currentProgress();
        Repayment repayment = target.getRepayment();
        leftDays = repayment.nextDue();
        leftNeeds = target.needMoney();
        lifeOfLoan = repayment.getDuration();
        totalLoan = target.getMoney();
        picPath = target.getProof();
        payWay = repayment.getType();
        useWay = target.getProjectDescription();
        monthInterest = repaymentRecord.getInterest();
        payAll = repaymentRecord.getPrincipal();
        PS = "";
        userLevel = target.getUser().getCreditRating();
        projectLevel = target.getTargetRating();
    }

    @Override
    public String toString() {
        return "TargetDetails{" +
                "progress=" + progress +
                ", leftDays=" + leftDays +
                ", leftNeeds=" + leftNeeds +
                ", lifeOfLoan=" + lifeOfLoan +
                ", totalLoan=" + totalLoan +
                ", picPath='" + picPath + '\'' +
                ", payWay=" + payWay +
                ", useWay='" + useWay + '\'' +
                ", monthInterest=" + monthInterest +
                ", payAll=" + payAll +
                ", PS='" + PS + '\'' +
                ", userLevel=" + userLevel +
                ", projectLevel=" + projectLevel +
                ", username='" + username + '\'' +
                '}';
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Long getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(Long leftDays) {
        this.leftDays = leftDays;
    }

    public Double getLeftNeeds() {
        return leftNeeds;
    }

    public void setLeftNeeds(Double leftNeeds) {
        this.leftNeeds = leftNeeds;
    }

    public Integer getLifeOfLoan() {
        return lifeOfLoan;
    }

    public void setLifeOfLoan(Integer lifeOfLoan) {
        this.lifeOfLoan = lifeOfLoan;
    }

    public Double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(Double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public RepaymentType getPayWay() {
        return payWay;
    }

    public void setPayWay(RepaymentType payWay) {
        this.payWay = payWay;
    }

    public String getUseWay() {
        return useWay;
    }

    public void setUseWay(String useWay) {
        this.useWay = useWay;
    }

    public Double getMonthInterest() {
        return monthInterest;
    }

    public void setMonthInterest(Double monthInterest) {
        this.monthInterest = monthInterest;
    }

    public Double getPayAll() {
        return payAll;
    }

    public void setPayAll(Double payAll) {
        this.payAll = payAll;
    }

    public String getPS() {
        return PS;
    }

    public void setPS(String PS) {
        this.PS = PS;
    }

    public CreditRating getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(CreditRating userLevel) {
        this.userLevel = userLevel;
    }

    public TargetRating getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(TargetRating projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
