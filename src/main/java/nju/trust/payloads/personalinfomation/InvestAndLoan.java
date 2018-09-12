package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class InvestAndLoan {
    private double totalInvestment; // 投资总额

    private double totalLoan;   // 借款总额

    private double getMoney;    // 待收回本息

    private double getMoneyProgress;    //本息收回进度

    private double payMoney;    //待偿还本息

    private double payMoneyProgress;    //本息偿还进度

    private double creditRatingScore;   // 信用评分

    private String creditRating;    // 信用评级

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public double getCreditRatingScore() {
        return creditRatingScore;
    }

    public void setCreditRatingScore(double creditRatingScore) {
        this.creditRatingScore = creditRatingScore;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public double getGetMoney() {
        return getMoney;
    }

    public void setGetMoney(double getMoney) {
        this.getMoney = getMoney;
    }

    public double getGetMoneyProgress() {
        return getMoneyProgress;
    }

    public void setGetMoneyProgress(double getMoneyProgress) {
        this.getMoneyProgress = getMoneyProgress;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public double getPayMoneyProgress() {
        return payMoneyProgress;
    }

    public void setPayMoneyProgress(double payMoneyProgress) {
        this.payMoneyProgress = payMoneyProgress;
    }
}
