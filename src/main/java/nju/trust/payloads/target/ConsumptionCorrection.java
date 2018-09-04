package nju.trust.payloads.target;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class ConsumptionCorrection {

    /**
     * 下次还款时间
     */
    private long nextDue;

    private List<Double> surplusRatios;

    private List<Double> discRatios;

    private List<Double> extraIncomes;

    public ConsumptionCorrection(long nextDue, List<Double> surplusRatios,
                                 List<Double> discRatios, List<Double> extraIncomes) {
        this.nextDue = nextDue;
        this.surplusRatios = surplusRatios;
        this.discRatios = discRatios;
        this.extraIncomes = extraIncomes;
    }

    public long getNextDue() {
        return nextDue;
    }

    public List<Double> getSurplusRatios() {
        return surplusRatios;
    }

    public List<Double> getDiscRatios() {
        return discRatios;
    }

    public List<Double> getExtraIncomes() {
        return extraIncomes;
    }
}
