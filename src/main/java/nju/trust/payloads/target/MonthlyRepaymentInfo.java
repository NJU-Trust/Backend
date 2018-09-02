package nju.trust.payloads.target;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class MonthlyRepaymentInfo {

    private double sum;

    private double principal;

    private double interest;

    private double remainPrincipal;

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

    public double getRemainPrincipal() {
        return remainPrincipal;
    }

    public void setRemainPrincipal(double remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }
}
