package nju.trust.service.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.target.ConsumptionCorrection;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
class ConsumptionCorrectionEvaluator {

    private double remainingSurplus;

    private double surplusPerMonth;

    private double discPerMonth;

    private double debt;

    private BaseTarget target;

    private List<Double> monthlyRepayment;

    ConsumptionCorrectionEvaluator(List<UserMonthStatistics> monthlyData, BaseTarget target,
                                   List<Double> monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
        this.target = target;
        init(monthlyData);
    }

    ConsumptionCorrection evaluate() {

        Repayment repayment = target.getRepayment();
        int duration = target.getRepaymentDuration();
        long nextDue = repayment.nextDue();

        return getConsumptionCorrection(duration, nextDue);
    }

    ConsumptionCorrection getConsumptionCorrection(int duration, long nextDue) {
        double remainingS = remainingSurplus + surplusPerMonth - debt;
        double remainingD = discPerMonth;
        List<Double> surplusRatios = new ArrayList<>();
        List<Double> discRatios = new ArrayList<>();
        List<Double> needExtraIncomes = new ArrayList<>();

        for (int i = 0; i < duration; i++) {
            double thisMonth = monthlyRepayment.get(i);
            remainingS -= thisMonth;
            if (remainingS < 0) {
                surplusRatios.add(100.);
                remainingD += remainingS;
                if (remainingD < 0) {
                    discRatios.add(100.);
                    needExtraIncomes.add(-remainingD);
                } else {
                    discRatios.add((-remainingS) / (remainingD - remainingS));
                    needExtraIncomes.add(0.);
                }
            } else {
                surplusRatios.add(thisMonth / (thisMonth + remainingS));
                discRatios.add(0.);
                needExtraIncomes.add(0.);
            }

            remainingS = Math.max(surplusPerMonth, remainingS + surplusPerMonth);
            remainingD = discPerMonth;
        }

        return new ConsumptionCorrection(nextDue, surplusRatios, discRatios, needExtraIncomes);
    }

    private void init(List<UserMonthStatistics> monthlyData) {
        UserMonthlyDataHelper helper = new UserMonthlyDataHelper(monthlyData);
        remainingSurplus = helper.getTotalSurplus();
        surplusPerMonth = helper.forecastSurplus();
        discPerMonth = helper.forecastDisc();
        debt = helper.getCurrentDebt();

        // Check whether duration equals size of monthlyRepayment
//        check();
    }

    private void check() {
        if (target.getRepaymentDuration() != monthlyRepayment.size())
            throw new ResourceNotFoundException("Monthly repayment information not found");
    }
}
