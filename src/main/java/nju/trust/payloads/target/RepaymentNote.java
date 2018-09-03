package nju.trust.payloads.target;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description: 还款注意事项
 */
public class RepaymentNote {

    private final List<Integer> exceedSurplusMonths;

    private final List<Double> discRatios;

    /**
     * 需要有额外收入的月份
     */
    private final List<Integer> needIncomeMonths;

    /**
     * 难度，百分比形式
     */
    private final Double difficulty;


    public RepaymentNote(List<Integer> exceedSurplusMonths, List<Double> discRatios,
                         List<Integer> needIncomeMonths, Double difficulty) {
        this.exceedSurplusMonths = exceedSurplusMonths;
        this.discRatios = discRatios;
        this.needIncomeMonths = needIncomeMonths;
        this.difficulty = difficulty;
    }

    public List<Integer> getExceedSurplusMonths() {
        return exceedSurplusMonths;
    }

    public List<Double> getDiscRatios() {
        return discRatios;
    }

    public List<Integer> getNeedIncomeMonths() {
        return needIncomeMonths;
    }

    public Double getDifficulty() {
        return difficulty;
    }
}
