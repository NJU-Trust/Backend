package nju.trust.service.target;

import nju.trust.entity.user.RepaymentType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: J.D. Liao
 * Date: 2018/9/7
 * Description:
 */
public class RepaymentCalculatorTest {

    @Test
    public void test1() {
        RepaymentCalculator repaymentCalculator = RepaymentCalculator.getCalculator(RepaymentType.EQUAL_PRINCIPAL,
                10e4, 240, 4.9);

        repaymentCalculator.monthlyRepayment.forEach(System.out::println);

    }

    @Test
    public void test2() {
        RepaymentCalculator repaymentCalculator = RepaymentCalculator
                .getCalculator(RepaymentType.EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST,
                10e4, 240, 4.9);

        repaymentCalculator.monthlyRepayment.forEach(System.out::println);

    }

    @Test
    public void test3() {
        RepaymentCalculator repaymentCalculator = RepaymentCalculator
                .getCalculator(RepaymentType.PRE_INTEREST,
                        10e4, 240, 4.9);

        repaymentCalculator.monthlyRepayment.forEach(System.out::println);
    }

    @Test
    public void test4() {
        RepaymentCalculator repaymentCalculator = RepaymentCalculator
                .getCalculator(RepaymentType.ONE_TIME_PAYMENT,
                        10e4, 240, 4.9);

        repaymentCalculator.monthlyRepayment.forEach(System.out::println);
    }
}