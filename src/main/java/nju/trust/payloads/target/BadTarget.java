package nju.trust.payloads.target;

/**
 * @Author: 许杨
 * @Description: 坏账
 * @Date: 2018/10/28
 */
public class BadTarget {
    private Long targetId;          // 项目编号
    private String projectName;     // 项目名称
    private String loanFrom;        // 借款人姓名
    private String badStartDate;    // 坏账发生日期 yyyy/mm/dd
    private double investAmount;    // 投资金额
    private double lossAmount;      // 损失金额
    private String state;           // 状态，正在追回 | 已结束

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLoanFrom() {
        return loanFrom;
    }

    public void setLoanFrom(String loanFrom) {
        this.loanFrom = loanFrom;
    }

    public String getBadStartDate() {
        return badStartDate;
    }

    public void setBadStartDate(String badStartDate) {
        this.badStartDate = badStartDate;
    }

    public double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(double investAmount) {
        this.investAmount = investAmount;
    }

    public double getLossAmount() {
        return lossAmount;
    }

    public void setLossAmount(double lossAmount) {
        this.lossAmount = lossAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
