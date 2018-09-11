package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class InvestandLoan {
    /*
    totalInvestment	double	投资总额
    totalLoan	double	借款总额
    pendingPrincipal	double	待收回本金
    pendingInterest	double	待收回利息
    creditRatingScore	double	信用评分
    creditRating	string	信用评级
     */
    private double totalInvestment;

    private double totalLoan;

    private double pendingPrincipal;

    private double pendingInterest;

    private double creditRatingScore;

    private String creditRating;

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
