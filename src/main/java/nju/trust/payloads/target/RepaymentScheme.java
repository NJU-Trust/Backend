package nju.trust.payloads.target;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/31
 * Description:
 */
public class RepaymentScheme {

    private Boolean exceedSurplus;

    private int[] exceedSurplusMonths;

    private Boolean exceedDisc;

    private double[] ratios;

    private int[] income;

    /**
     * 难度，百分比形式
     */
    private Double difficulty;

    private Double interest;

    /**
     * 本息和
     */
    private Double sum;

    private List<MonthlyRepaymentInfo> monthlyData;



}
