package nju.trust.payloads.target;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class RepaymentTotalInfo {

    /**
     * 利息总额
     */
    private Double interest;

    /**
     * 还款总额
     */
    private Double sum;

    private List<RepaymentMonthInfo> monthlyData;

    private RepaymentNote note;

    public RepaymentTotalInfo(Double interest, Double sum, List<RepaymentMonthInfo> monthlyData, RepaymentNote note) {
        this.interest = interest;
        this.sum = sum;
        this.monthlyData = monthlyData;
        this.note = note;
    }

    public RepaymentNote getNote() {
        return note;
    }

    public void setNote(RepaymentNote note) {
        this.note = note;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<RepaymentMonthInfo> getMonthlyData() {
        return monthlyData;
    }

    public void setMonthlyData(List<RepaymentMonthInfo> monthlyData) {
        this.monthlyData = monthlyData;
    }
}
