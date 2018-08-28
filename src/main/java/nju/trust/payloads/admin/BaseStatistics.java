package nju.trust.payloads.admin;

/**
 * @Author: 许杨
 * @Description: 管理员进行数据统计时展示的基础数据
 * @Date: 2018/8/28
 */
public class BaseStatistics {
    // 总额
    private String dealMoneySum = "";// 交易总额
    private String dealNum = "";// 交易总笔数
    private String borrowerNum = "";// 借款人数量
    private String investorNum = "";// 投资人数量
    // 人均统计
    private String loanPerPerson = "";// 人均累计借款额度
    private String loanPerTarget = "";// 笔均借款额度
    private String investmentPerPerson = "";// 人均累计投资额度
    // 其他统计
    private String mostLoanPersonRate = "";// 最大单户借款余额占比
    private String most10LoanPersonRate = "";// 最大10户借款余额占比
    private String averageGoingTime = "";// 平均满标时间

    public String getDealMoneySum() {
        return dealMoneySum;
    }

    public void setDealMoneySum(String dealMoneySum) {
        this.dealMoneySum = dealMoneySum;
    }

    public String getDealNum() {
        return dealNum;
    }

    public void setDealNum(String dealNum) {
        this.dealNum = dealNum;
    }

    public String getBorrowerNum() {
        return borrowerNum;
    }

    public void setBorrowerNum(String borrowerNum) {
        this.borrowerNum = borrowerNum;
    }

    public String getInvestorNum() {
        return investorNum;
    }

    public void setInvestorNum(String investorNum) {
        this.investorNum = investorNum;
    }

    public String getLoanPerPerson() {
        return loanPerPerson;
    }

    public void setLoanPerPerson(String loanPerPerson) {
        this.loanPerPerson = loanPerPerson;
    }

    public String getLoanPerTarget() {
        return loanPerTarget;
    }

    public void setLoanPerTarget(String loanPerTarget) {
        this.loanPerTarget = loanPerTarget;
    }

    public String getInvestmentPerPerson() {
        return investmentPerPerson;
    }

    public void setInvestmentPerPerson(String investmentPerPerson) {
        this.investmentPerPerson = investmentPerPerson;
    }

    public String getMostLoanPersonRate() {
        return mostLoanPersonRate;
    }

    public void setMostLoanPersonRate(String mostLoanPersonRate) {
        this.mostLoanPersonRate = mostLoanPersonRate;
    }

    public String getMost10LoanPersonRate() {
        return most10LoanPersonRate;
    }

    public void setMost10LoanPersonRate(String most10LoanPersonRate) {
        this.most10LoanPersonRate = most10LoanPersonRate;
    }

    public String getAverageGoingTime() {
        return averageGoingTime;
    }

    public void setAverageGoingTime(String averageGoingTime) {
        this.averageGoingTime = averageGoingTime;
    }
}
