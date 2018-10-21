package nju.trust.payloads.admin;

/**
 * @Author: 许杨
 * @Description: 管理员进行数据统计时展示的违约数据
 * 逾期项目：未按时还清所有款项的项目（逾期金额不为0）
 * @Date: 2018/8/28
 */
public class BreakContractStatistics {
    private static final String NOTCARE = "/";

    private String DefaultRate = "";            // 累计违约率=本月违约项目/本月正在进行中的项目总数（包括上个月遗留下来的和这个月新发起的）
    private String overdueProgramNum = "";      // 逾期项目数=本月逾期项目
    private String overdueProgramRate = "";     // 项目逾期率=本月逾期项目数/本月需还款的项目总数
    private String overdueProgramRate3 = "";    // 近三月项目逾期率=近三月逾期项目数/近三月需还款的项目总数
    private String overdueMoneySum = "";        // 借款逾期金额=本月应交而未交的金额（若是应该本月3号交，但5号才交算为本月借款逾期金额）
    private String toPay = "";                  // 待偿金额=本月要偿还但还未偿还的总金额
    private String overdueMoneyRate = "";       // 借贷金额逾期率=本月已经逾期的金额/本月总借款金额
    private String badDebtRate = "";            // 借贷坏账率自平台开始运营后30天未还款的还款额/自平台开始运营后的项目总金额
    private String complaints = "";             // 客户投诉情况 默认为0

    public String getDefaultRate() {
        return DefaultRate;
    }

    public void setDefaultRate(double defaultRate) {
        DefaultRate = toPercent(defaultRate);
    }

    public String getOverdueProgramNum() {
        return overdueProgramNum;
    }

    public void setOverdueProgramNum(int overdueProgramNum) {
        this.overdueProgramNum = toForm(overdueProgramNum);
    }

    public String getOverdueProgramRate() {
        return overdueProgramRate;
    }

    public void setOverdueProgramRate(double overdueProgramRate) {
        this.overdueProgramRate = toPercent(overdueProgramRate);
    }

    public String getOverdueProgramRate3() {
        return overdueProgramRate3;
    }

    public void setOverdueProgramRate3(double overdueProgramRate3) {
        this.overdueProgramRate3 = toPercent(overdueProgramRate3);
    }

    public String getOverdueMoneySum() {
        return overdueMoneySum;
    }

    public void setOverdueMoneySum(double overdueMoneySum) {
        this.overdueMoneySum = toForm(overdueMoneySum);
    }

    public String getToPay() {
        return toPay;
    }

    public void setToPay(double toPay) {
        this.toPay = toForm(toPay);
    }

    public String getOverdueMoneyRate() {
        return overdueMoneyRate;
    }

    public void setOverdueMoneyRate(double overdueMoneyRate) {
        this.overdueMoneyRate = toPercent(overdueMoneyRate);
    }

    public String getBadDebtRate() {
        return badDebtRate;
    }

    public void setBadDebtRate(double badDebtRate) {
        this.badDebtRate = toPercent(badDebtRate);
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(int complaints) {
        this.complaints = toForm(complaints);
    }

    // 保留两位小数
    private String toForm(double num) {
        if(num >= 0) {
            return String.format("%.2f", num);
        }else {
            return NOTCARE;
        }
    }

    // 得到string
    private String toForm(int num) {
        if(num >= 0) {
            return String.valueOf(num);
        }else {
            return NOTCARE;
        }
    }

    // 得到xx.xx%格式的百分数
    private String toPercent(double num) {
        if(num >= 0) {
            return String.format("%.2f", num*100)+"%";
        }else {
            return NOTCARE;
        }
    }
}
