package nju.trust.service.target;

import nju.trust.entity.user.UserMonthStatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
class RepaymentNoteHelper {

    private double remainingSurplus;

    private double surplusPerMonth;

    private double discPerMonth;

    /**
     * 现有负债
     */
    private double debt;

    private double duration;

    private double totalRepayment;

    private List<Double> monthlyRepayment;

    RepaymentNoteHelper(double remainingSurplus, double surplusPerMonth,
                        double discPerMonth, double debt, double duration, double totalRepayment,
                        List<Double> monthlyRepayment) {
        this.remainingSurplus = remainingSurplus;
        this.surplusPerMonth = surplusPerMonth;
        this.discPerMonth = discPerMonth;
        this.debt = debt;
        this.duration = duration;
        this.totalRepayment = totalRepayment;
        this.monthlyRepayment = monthlyRepayment;

        if (monthlyRepayment.size() < duration)
            throw new IllegalStateException("Wrong data");
    }

    RepaymentNoteHelper(List<UserMonthStatistics> monthlyData, double duration,
                        List<Double> monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
        this.duration = duration;
        init(monthlyData);
    }

    double evaluateDifficulty() {
        double totalSurplus = remainingSurplus + duration * surplusPerMonth;
        double totalDisc = duration * discPerMonth;
        double difficulty;

        if (totalRepayment <= (totalSurplus - debt))
            difficulty = totalRepayment / (totalSurplus - debt) * 20.;
        else if (totalRepayment - totalSurplus + debt <= totalDisc)
            difficulty = 20. + (totalRepayment - totalSurplus + debt) / totalDisc * 60.;
        else
            difficulty = 100.;

        return difficulty;
    }

    List<Integer> evaluateSurplus() {
        double remaining = remainingSurplus + surplusPerMonth - debt;
        List<Integer> months = new ArrayList<>();
        for (int i = 0; i < duration; i++) {
            remaining -= monthlyRepayment.get(i);
            if (remaining < 0) {
                months.add(i + 1);
                remaining = surplusPerMonth;
            } else {
                remaining += surplusPerMonth;
            }
        }

        return months;
    }

    List<Integer> evaluateDisc() {
        double remainingS = remainingSurplus + surplusPerMonth - debt;
        double remainingD = discPerMonth;
        List<Integer> months = new ArrayList<>();

        for (int i = 0; i < duration; i++) {
            remainingS -= monthlyRepayment.get(i);
            if (remainingS < 0) {
                remainingD += remainingS;
            }

            if (remainingD < 0) {
                months.add(i + 1);
            }

            remainingS = Math.max(surplusPerMonth, remainingS + surplusPerMonth);
            remainingD = discPerMonth;
        }

        return months;
    }

    private void init(List<UserMonthStatistics> monthlyData) {
        UserMonthlyDataHelper helper = new UserMonthlyDataHelper(monthlyData);
        remainingSurplus = helper.getTotalSurplus();
        surplusPerMonth = helper.forecastSurplus();
        discPerMonth = helper.forecastDisc();
        debt = helper.getCurrentDebt();

        totalRepayment = monthlyRepayment.stream().mapToDouble(t -> t).sum();
    }
}
