package nju.trust.service.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
class TargetEvaluator {

    private BaseTarget target;

    private long numberOfSuccess;

    TargetEvaluator(BaseTarget target, long numberOfSuccess) {
        this.numberOfSuccess = numberOfSuccess;
        this.target = target;
    }

    double evaluate() {
        User user = target.getUser();

        double money = target.getMoney();
        double interestRate = target.getRepayment().getYearInterestRate();
        int duration = target.getRepayment().getRepaymentDuration();

        CreditRating creditRating = CreditRating.of(user.getCreditScore());
        UserMonthlyDataHelper dataHelper = new UserMonthlyDataHelper(user.getMonthStatistics());
        double monthIncome = dataHelper.getAvgMonthlyIncomeLevel();

        // Calculate z-value
        double z = 1.18 - 0.35 * money * 0.001 - 0.82 * interestRate + 1.84 * creditRating.getLevel()
                + 0.04 * duration + 0.48 * monthIncome + 1.96 * numberOfSuccess;

        return 1. / (1 + Math.exp(-z));
    }
}
