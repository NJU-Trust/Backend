package nju.trust.service.target;

import nju.trust.entity.record.RepaymentRecord;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class PayItem {

    private String payTitle;

    private LocalDate projectTime;

    private Integer times;

    private Double interestPlus;

    private Integer timesA;

    private LocalDate timeA;

    private Double amountA;

    private Integer timesB;

    private LocalDate timeB;

    private Double amountB;

    private Integer timesC;

    private LocalDate timeC;

    private Double amountC;

    public PayItem(String payTitle, LocalDate projectTime, Integer times,
                   Double interestPlus) {
        this.payTitle = payTitle;
        this.projectTime = projectTime;
        this.times = times;
        this.interestPlus = interestPlus;
    }

    void setA(RepaymentRecord record) {
        timesA = record.getPeriod();
        timeA = record.getReturnDate();
        amountA = record.getSum();
    }

    void setB(RepaymentRecord record) {
        timesB = record.getPeriod();
        timeB = record.getReturnDate();
        amountB = record.getSum();
    }

    void setC(RepaymentRecord record) {
        timesC = record.getPeriod();
        timeC = record.getReturnDate();
        amountC = record.getSum();
    }

    public String getPayTitle() {
        return payTitle;
    }

    public LocalDate getProjectTime() {
        return projectTime;
    }

    public Integer getTimes() {
        return times;
    }

    public Double getInterestPlus() {
        return interestPlus;
    }

    public Integer getTimesA() {
        return timesA;
    }

    public LocalDate getTimeA() {
        return timeA;
    }

    public Double getAmountA() {
        return amountA;
    }

    public Integer getTimesB() {
        return timesB;
    }

    public LocalDate getTimeB() {
        return timeB;
    }

    public Double getAmountB() {
        return amountB;
    }

    public Integer getTimesC() {
        return timesC;
    }

    public LocalDate getTimeC() {
        return timeC;
    }

    public Double getAmountC() {
        return amountC;
    }
}
