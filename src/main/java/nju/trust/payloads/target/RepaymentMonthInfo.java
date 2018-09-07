package nju.trust.payloads.target;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class RepaymentMonthInfo {

    private double sum;

    private double principal;

    private double interest;

    private double remainingPrincipal;

    public RepaymentMonthInfo(double sum, double principal, double interest, double remainingPrincipal) {
        this.sum = sum;
        this.principal = principal;
        this.interest = interest;
        this.remainingPrincipal = remainingPrincipal;
    }

    @Override
    public String toString() {
        return "RepaymentMonthInfo{" +
                "sum=" + keep2dot(sum) +
                ", principal=" + keep2dot(principal) +
                ", interest=" + keep2dot(interest) +
                ", remainingPrincipal=" + keep2dot(remainingPrincipal) +
                '}';
    }

    private double keep2dot(double d) {
        return (double) Math.round(d * 100) / 100;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(double remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }
}
