package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description: 用户平台内资金信息
 * @Date: 2018/9/11
 */
public class TotalAccountInfo {
    private double totalAccount;    // 账户总额

    // 可提现、可用于投资的流动余额（投资后转入冻结金额）
    private double balance; // 账户余额

    // 投资出去还没成交的部分（投资人视角，成交后转入待回收本息）
    private double frozenAmount;    // 冻结金额

    // 成交后的冻结金额（投资人视角）
    private double pendingPI;   // 待回收本息(PI指 principal interst)

    // 借款有人投进来但标的还没成交的（借款人视角，成交后转入账户余额）
    private double investmentInBidding; // 招标中投资

    public double getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(double totalAccount) {
        this.totalAccount = totalAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public double getPendingPI() {
        return pendingPI;
    }

    public void setPendingPI(double pendingPI) {
        this.pendingPI = pendingPI;
    }

    public double getInvestmentInBidding() {
        return investmentInBidding;
    }

    public void setInvestmentInBidding(double investmentInBidding) {
        this.investmentInBidding = investmentInBidding;
    }
}
