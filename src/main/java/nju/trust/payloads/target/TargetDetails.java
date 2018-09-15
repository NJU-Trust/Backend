package nju.trust.payloads.target;

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

    private UserLevel userLevel;

    private TargetRating projectLevel;

    public TargetDetails(BaseTarget target, RepaymentRecord repaymentRecord) {
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
        userLevel = target.getUser().getUserLevel();
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
                '}';
    }
}
