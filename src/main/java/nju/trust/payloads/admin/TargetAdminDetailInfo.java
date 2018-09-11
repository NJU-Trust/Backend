package nju.trust.payloads.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import nju.trust.entity.target.TargetRating;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.user.RepaymentType;
import nju.trust.payloads.target.TargetInfo;

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
    private TargetState state;  // 标的状态
    private Double interestRate;    // 利率（同时也是项目收益情况）
    @JsonProperty("riskRating")
    private TargetRating targetRating;// 项目风险评级
    private RepaymentType type; // 还款方案

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
        return state;
    }

    public void setState(TargetState state) {
        this.state = state;
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
        return type;
    }

    public void setType(RepaymentType type) {
        this.type = type;
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
        this.state = state;
        this.interestRate = interestRate;
        this.targetRating = targetRating;
        this.type = type;
    }
    public TargetAdminDetailInfo(TargetInfo targetInfo, RepaymentType type) {
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
    }
}
