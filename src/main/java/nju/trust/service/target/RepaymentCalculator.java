package nju.trust.service.target;

import nju.trust.entity.user.RepaymentType;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.target.RepaymentMonthInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/9/1
 * Description:
 */
abstract class RepaymentCalculator {

    static RepaymentCalculator getCalculator(RepaymentType type, double principal, double duration,
                                             double interestRate) {
        RepaymentCalculator calculator;

        switch (type) {
            case EQUAL_PRINCIPAL:
                calculator = new EPRepaymentCalculator(principal, duration, interestRate);
                break;
            case PRE_INTEREST:
                calculator = new PIRepaymentCalculator(principal, duration, interestRate);
                break;
            case ONE_TIME_PAYMENT:
                calculator = new OTPRepaymentCalculator(principal, duration, interestRate);
                break;
            case EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST:
                calculator = new EIPIRepaymentCalculator(principal, duration, interestRate);
                break;
            default:
                throw new ResourceNotFoundException("Repayment type not found");
        }

        return calculator;
    }

    List<RepaymentMonthInfo> monthlyRepayment = new ArrayList<>();

    double principal;

    double duration;

    double interestRate;

    RepaymentCalculator(double principal, double duration, double interestRate) {
        this.principal = principal;
        this.duration = duration;
        this.interestRate = interestRate / 100 / 12;
        init();
    }

    double getTotalInterest() {
        return monthlyRepayment.stream().mapToDouble(RepaymentMonthInfo::getInterest).sum();
    }

    double getTotalRepayment() {
        return monthlyRepayment.stream().mapToDouble(RepaymentMonthInfo::getSum).sum();
    }

    List<Double> getMonthlyRepayment() {
        return monthlyRepayment.stream().map(RepaymentMonthInfo::getSum).collect(Collectors.toList());
    }


    abstract void init();
}
