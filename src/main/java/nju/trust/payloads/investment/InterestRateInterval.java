package nju.trust.payloads.investment;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class InterestRateInterval {

    /**
     * 所有标的中的最小利率
     */
    private double min;

    /**
     * 所有标的中的最大利率
     */
    private double max;

    public InterestRateInterval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
