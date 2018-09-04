package nju.trust.service.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;
import nju.trust.entity.user.UserMonthStatistics;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
class TargetEvaluator {

    private BaseTarget target;

    private long numberOfSuccess;

    private List<UserMonthStatistics> userMonthStatistics;

    TargetEvaluator(BaseTarget target, long numberOfSuccess, List<UserMonthStatistics> userMonthStatistics) {
        this.numberOfSuccess = numberOfSuccess;
        this.target = target;
        this.userMonthStatistics = userMonthStatistics;
    }

    double evaluate() {
        User user = target.getUser();

        double money = target.getMoney();
        double interestRate = target.getRepayment().getInterestRate();
        int duration = target.getRepayment().getDuration();

        CreditRating creditRating = CreditRating.of(user.getCreditScore());
        UserMonthlyDataHelper dataHelper = new UserMonthlyDataHelper(userMonthStatistics);
        double monthIncome = dataHelper.getAvgMonthlyIncomeLevel();

        // Calculate z-value
        double z = 1.18 - 0.35 * money * 0.001 - 0.82 * interestRate + 1.84 * creditRating.getLevel()
                + 0.04 * duration + 0.48 * monthIncome + 1.96 * numberOfSuccess;

        return 1. / (1 + Math.exp(-z));
    }
}
