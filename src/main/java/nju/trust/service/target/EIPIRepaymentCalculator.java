package nju.trust.service.target;

import nju.trust.payloads.target.RepaymentMonthInfo;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
public class EIPIRepaymentCalculator extends RepaymentCalculator {

    EIPIRepaymentCalculator(double principal, double duration, double interestRate) {
        super(principal, duration, interestRate);
    }

    @Override
    void init() {
        double remainingPrincipal = principal;
        for (int i = 0; i < duration; i++) {
            double sum = (principal * interestRate * Math.pow(1 + interestRate, duration))
                    / (Math.pow(1 + interestRate, duration) - 1);
            double principalPortion = (principal * interestRate * Math.pow(1 + interestRate, i))
                    / (Math.pow(1 + interestRate, duration) - 1);
            remainingPrincipal -= principalPortion;
            double interestPortion = sum - principalPortion;
            monthlyRepayment.add(new RepaymentMonthInfo(sum, principalPortion, interestPortion, remainingPrincipal));
        }
    }
}
