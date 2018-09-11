package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class InvestAndLoan {
    private double totalInvestment; // 投资总额

    private double totalLoan;   // 借款总额

    private double pendingPrincipal;    // 待收回本金

    private double pendingInterest; // 待收回利息

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

    public double getPendingPrincipal() {
        return pendingPrincipal;
    }

    public void setPendingPrincipal(double pendingPrincipal) {
        this.pendingPrincipal = pendingPrincipal;
    }

    public double getPendingInterest() {
        return pendingInterest;
    }

    public void setPendingInterest(double pendingInterest) {
        this.pendingInterest = pendingInterest;
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
}
