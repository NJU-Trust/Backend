package nju.trust.payloads.target;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class RepaymentRequest {
    private double money;

    private int duration;

    private double interestRate;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
