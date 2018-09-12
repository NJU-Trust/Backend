package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class TotalAccountInfo {

    //账户总额
    private double totalAccount;
    //账户余额
    private double balance;
    //冻结金额
    private double frozenAmount;
    //待回收本息(PI指 principal interst)
    private double pendingPI;
    //招标中投资
    private double investmentInBidding;

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
