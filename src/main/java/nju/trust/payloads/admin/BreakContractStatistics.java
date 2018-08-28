package nju.trust.payloads.admin;

/**
 * @Author: 许杨
 * @Description: 管理员进行数据统计时展示的违约数据
 * 逾期项目：未按时还清所有款项的项目（逾期金额不为0）
 * @Date: 2018/8/28
 */
// TODO 数据格式string改double或int
// TODO comlaints的格式改为list
public class BreakContractStatistics {
    private String DefaultRate = "";            // 累计违约率
    private String overdueProgramNum = "";      // 逾期项目数
    private String overdueProgramRate = "";     // 项目逾期率
    private String overdueProgramRate3 = "";    // 近三月项目逾期率
    private String overdueMoneySum = "";        // 借款逾期金额
    private String toPay = "";                  // 待偿金额
    private String overdueMoneyRate = "";       // 借贷金额逾期率
    private String badDebtRate = "";            // n=30 借贷坏账率
    private String complaints = "";             // 客户投诉情况

    public String getDefaultRate() {
        return DefaultRate;
    }

    public void setDefaultRate(String defaultRate) {
        DefaultRate = defaultRate;
    }

    public String getOverdueProgramNum() {
        return overdueProgramNum;
    }

    public void setOverdueProgramNum(String overdueProgramNum) {
        this.overdueProgramNum = overdueProgramNum;
    }

    public String getOverdueProgramRate() {
        return overdueProgramRate;
    }

    public void setOverdueProgramRate(String overdueProgramRate) {
        this.overdueProgramRate = overdueProgramRate;
    }

    public String getOverdueProgramRate3() {
        return overdueProgramRate3;
    }

    public void setOverdueProgramRate3(String overdueProgramRate3) {
        this.overdueProgramRate3 = overdueProgramRate3;
    }

    public String getOverdueMoneySum() {
        return overdueMoneySum;
    }

    public void setOverdueMoneySum(String overdueMoneySum) {
        this.overdueMoneySum = overdueMoneySum;
    }

    public String getToPay() {
        return toPay;
    }

    public void setToPay(String toPay) {
        this.toPay = toPay;
    }

    public String getOverdueMoneyRate() {
        return overdueMoneyRate;
    }

    public void setOverdueMoneyRate(String overdueMoneyRate) {
        this.overdueMoneyRate = overdueMoneyRate;
    }

    public String getBadDebtRate() {
        return badDebtRate;
    }

    public void setBadDebtRate(String badDebtRate) {
        this.badDebtRate = badDebtRate;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }
}
