package nju.trust.service.target;

import nju.trust.payloads.target.RepaymentMonthInfo;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
public class EPRepaymentCalculator extends RepaymentCalculator {

    public EPRepaymentCalculator(double principal, double duration, double interestRate) {
        super(principal, duration, interestRate);
    }

    @Override
    void init() {
        double repaidPrincipal = 0.;
        double principalPortion = principal / duration;
        for (int i = 0; i < duration; i++) {
            double interestPortion = (principal - repaidPrincipal) * interestRate;
            double sum = principalPortion + interestPortion;

            repaidPrincipal += principalPortion;
            RepaymentMonthInfo monthInfo = new RepaymentMonthInfo(sum, principalPortion,
                    interestPortion, principal - repaidPrincipal);
            monthlyRepayment.add(monthInfo);
        }
    }
}
