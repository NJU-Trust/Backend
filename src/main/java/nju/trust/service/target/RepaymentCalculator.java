package nju.trust.service.target;

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

    List<RepaymentMonthInfo> monthlyRepayment = new ArrayList<>();

    double principal;

    double duration;

    double interestRate;

    RepaymentCalculator(double principal, double duration, double interestRate) {
        this.principal = principal;
        this.duration = duration;
        this.interestRate = interestRate;
        init();
    }

    public double getTotalInterest() {
        return monthlyRepayment.stream().mapToDouble(RepaymentMonthInfo::getInterest).sum();
    }

    public double getTotalRepayment() {
        return monthlyRepayment.stream().mapToDouble(RepaymentMonthInfo::getSum).sum();
    }

    public List<Double> getMonthlyRepayment() {
        return monthlyRepayment.stream().map(RepaymentMonthInfo::getSum).collect(Collectors.toList());
    }



    abstract void init();
}
