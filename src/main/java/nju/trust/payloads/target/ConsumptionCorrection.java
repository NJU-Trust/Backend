package nju.trust.payloads.target;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class ConsumptionCorrection {

    private LocalDate date;

    /**
     * 结余占用率
     */
    private double surplusRatio;
    /**
     * 可调支出占用率
     */
    private double discRatio;
    /**
     * 额外收入金额
     */
    private double extraIncome;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSurplusRatio() {
        return surplusRatio;
    }

    public void setSurplusRatio(double surplusRatio) {
        this.surplusRatio = surplusRatio;
    }

    public double getDiscRatio() {
        return discRatio;
    }

    public void setDiscRatio(double discRatio) {
        this.discRatio = discRatio;
    }

    public double getExtraIncome() {
        return extraIncome;
    }

    public void setExtraIncome(double extraIncome) {
        this.extraIncome = extraIncome;
    }
}
