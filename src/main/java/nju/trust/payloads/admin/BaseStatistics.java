package nju.trust.payloads.admin;

/**
 * @Author: 许杨
 * @Description: 管理员进行数据统计时展示的基础数据
 * @Date: 2018/8/28
 */
public class BaseStatistics {
    private static final String NOTCARE = "/";
    // 总额
    private String dealMoneySum = NOTCARE;// 交易总额
    private String dealNum = NOTCARE;// 交易总笔数
    private String borrowerNum = NOTCARE;// 借款人数量
    private String investorNum = NOTCARE;// 投资人数量
    // 人均统计
    private String loanPerPerson = NOTCARE;// 人均累计借款额度
    private String loanPerTarget = NOTCARE;// 笔均借款额度
    private String investmentPerPerson = NOTCARE;// 人均累计投资额度
    // 其他统计
    private String mostLoanPersonRate = NOTCARE;// 最大单户借款余额占比
    private String most10LoanPersonRate = NOTCARE;// 最大10户借款余额占比
    private String averageGoingTime = NOTCARE;// 平均满标时间（以天为单位）

    public String getDealMoneySum() {
        return dealMoneySum;
    }

    public void setDealMoneySum(double dealMoneySum) {
        this.dealMoneySum = toForm(dealMoneySum);
    }

    public String getDealNum() {
        return dealNum;
    }

    public void setDealNum(int dealNum) {
        this.dealNum = toForm(dealNum);
    }

    public String getBorrowerNum() {
        return borrowerNum;
    }

    public void setBorrowerNum(int borrowerNum) {
        this.borrowerNum = toForm(borrowerNum);
    }

    public String getInvestorNum() {
        return investorNum;
    }

    public void setInvestorNum(int investorNum) {
        this.investorNum = toForm(investorNum);
    }

    public String getLoanPerPerson() {
        return loanPerPerson;
    }

    public void setLoanPerPerson(double loanPerPerson) {
        this.loanPerPerson = toForm(loanPerPerson);
    }

    public String getLoanPerTarget() {
        return loanPerTarget;
    }

    public void setLoanPerTarget(double loanPerTarget) {
        this.loanPerTarget = toForm(loanPerTarget);
    }

    public String getInvestmentPerPerson() {
        return investmentPerPerson;
    }

    public void setInvestmentPerPerson(double investmentPerPerson) {
        this.investmentPerPerson = toForm(investmentPerPerson);
    }

    public String getMostLoanPersonRate() {
        return mostLoanPersonRate;
    }

    public void setMostLoanPersonRate(double mostLoanPersonRate) {
        this.mostLoanPersonRate = toPercent(mostLoanPersonRate);
    }

    public String getMost10LoanPersonRate() {
        return most10LoanPersonRate;
    }

    public void setMost10LoanPersonRate(double most10LoanPersonRate) {
        this.most10LoanPersonRate = toPercent(most10LoanPersonRate);
    }

    public String getAverageGoingTime() {
        return averageGoingTime;
    }

    public void setAverageGoingTime(double averageGoingTime) {
        this.averageGoingTime = toAverageDayForm(averageGoingTime);
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

    // 平均满标时间
    // 不计算：/   计算：xx.xx天
    private String toAverageDayForm(double num) {
        if(num >= 0) {
            return toForm(num)+"天";
        }else {
            return NOTCARE;
        }
    }
}