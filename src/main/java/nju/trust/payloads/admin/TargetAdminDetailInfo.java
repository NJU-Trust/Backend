package nju.trust.payloads.admin;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetRating;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.RepaymentType;

import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/2
 */
public class TargetAdminDetailInfo {
    private Long id;    // 编号
    private String username;    // 用户名
    private LocalDate startTime;    // 起始时间
    private Double money;   // 金额
    private Double collectedMoney;  // 已筹集金额
    private String projectDescription;  // 项目说明
    private String state;  // 标的状态
    private Double interestRate;    // 利率（同时也是项目收益情况）
    private TargetRating targetRating;// 项目风险评级
    private String type; // 还款方案

    public TargetAdminDetailInfo(BaseTarget baseTarget, Repayment repayment) {
        this.id = baseTarget.getId();
        this.username = baseTarget.getUser().getUsername();
        this.startTime = baseTarget.getStartTime();
        this.money = baseTarget.getMoney();
        this.collectedMoney = baseTarget.getCollectedMoney();
        this.projectDescription = baseTarget.getProjectDescription();
        this.state = baseTarget.getTargetState().getStr();
        this.interestRate = repayment.getInterestRate();
        this.targetRating = baseTarget.getTargetRating();
        this.type = repayment.getType().getStr();
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public TargetState getState() {
        return TargetState.getEnum(state);
    }

    public void setState(TargetState state) {
        this.state = state.getStr();
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public TargetRating getTargetRating() {
        return targetRating;
    }

    public void setTargetRating(TargetRating targetRating) {
        this.targetRating = targetRating;
    }

    public RepaymentType getType() {
        return RepaymentType.getEnum(type);
    }

    public void setType(RepaymentType type) {
        this.type = type.getStr();
    }

    public TargetAdminDetailInfo() {
    }
    public TargetAdminDetailInfo(Long id, String username, LocalDate startTime, Double money, Double collectedMoney, String projectDescription, TargetState state, Double interestRate, TargetRating targetRating, RepaymentType type) {
        this.id = id;
        this.username = username;
        this.startTime = startTime;
        this.money = money;
        this.collectedMoney = collectedMoney;
        this.projectDescription = projectDescription;
        this.state = state.getStr();
        this.interestRate = interestRate;
        this.targetRating = targetRating;
        this.type = type.getStr();
    }
/*    public TargetAdminDetailInfo(TargetInfo targetInfo, RepaymentType type) {
        if(targetInfo == null) {
            System.out.println("targetInfo == null");
        }
        if(targetInfo.getId() == null) {
            System.out.println("targetInfo.getId() == null");
        }
        this.id = targetInfo.getId();
        this.username = targetInfo.getUsername();
        this.startTime = targetInfo.getStartTime();
        this.money = targetInfo.getMoney();
        this.collectedMoney = targetInfo.getCollectedMoney();
        this.projectDescription = targetInfo.getProjectDescription();
        this.state = targetInfo.getState();
        this.interestRate = targetInfo.getInterestRate();
        this.targetRating = targetInfo.getTargetRating();
        this.type = type;
    }*/

    public void print() {
        System.out.println("detailInfo:");
        System.out.println("id:"+this.id);
        System.out.println("username:"+this.username);
        System.out.println("startTime:"+this.startTime);
        System.out.println("money:"+this.money);
        System.out.println("collectedMoney:"+this.collectedMoney);
        System.out.println("projectDescription:"+this.projectDescription);
        System.out.println("state:"+this.state);
        System.out.println("interestRate:"+this.interestRate);
        System.out.println("targetRating:"+this.targetRating);
        System.out.println("type:"+this.type);
    }
}
