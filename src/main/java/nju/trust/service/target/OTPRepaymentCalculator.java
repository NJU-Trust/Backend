package nju.trust.service.target;

import nju.trust.payloads.target.RepaymentMonthInfo;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
class OTPRepaymentCalculator extends RepaymentCalculator {

    OTPRepaymentCalculator(double principal, double duration, double interestRate) {
        super(principal, duration, interestRate);
    }

    @Override
    void init() {
        double interestPortion = principal * interestRate * duration;
        for (int i = 0; i < duration - 1; i++) {
            monthlyRepayment.add(new RepaymentMonthInfo(0, 0, 0, principal));
        }
        monthlyRepayment.add(new RepaymentMonthInfo(interestPortion + principal, principal,
                interestPortion, 0.));
    }
}
