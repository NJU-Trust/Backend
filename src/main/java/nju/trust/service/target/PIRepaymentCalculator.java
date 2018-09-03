package nju.trust.service.target;

import nju.trust.payloads.target.RepaymentMonthInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/3
 * Description:
 */
public class PIRepaymentCalculator extends RepaymentCalculator {
    PIRepaymentCalculator(double principal, double duration, double interestRate) {
        super(principal, duration, interestRate);
    }

    @Override
    void init() {
        double interestPortion = principal * interestRate;
        for (int i = 0; i < duration - 1; i++) {
            monthlyRepayment.add(new RepaymentMonthInfo(interestPortion, 0., interestPortion, principal));
        }
        monthlyRepayment.add(new RepaymentMonthInfo(interestPortion + principal, principal,
                interestPortion, 0.));
    }
}
