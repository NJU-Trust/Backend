package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class SmallTargetFilterRequest {

    public static SmallTargetFilterRequest testData() {
        SmallTargetFilterRequest filter = new SmallTargetFilterRequest();
        filter.setInterestRate(new Double[]{0., 0.});
        filter.setMoney(new Double[]{0., 0.});
        filter.setRepaymentDuration(new Integer[]{0, 0});
        filter.setTime(new @Size(min = 2, max = 2) LocalDate[]{LocalDate.now(), LocalDate.now()});
        filter.setUseOfFunds(new ArrayList<>());
        filter.setUserCreditRating(new CreditRating[]{});

        return filter;
    }

    @Size(min = 2, max = 2)
    private Double[] money;

    private @Size(min = 2, max = 2) LocalDate[] time;

    @Size(min = 2, max = 2)
    private Double[] interestRate;

    @Size(min = 2, max = 2)
    private Integer[] repaymentDuration;

    private CreditRating[] userCreditRating;

    private List<String> useOfFunds;

    @Override
    public String toString() {
        return "SmallTargetFilterRequest{" +
                "money=" + Arrays.toString(money) +
                ", time=" + Arrays.toString(time) +
                ", interestRate=" + Arrays.toString(interestRate) +
                ", repaymentDuration=" + Arrays.toString(repaymentDuration) +
                ", userCreditRating=" + Arrays.toString(userCreditRating) +
                ", useOfFunds=" + useOfFunds +
                '}';
    }

    public List<String> getUseOfFunds() {
        return useOfFunds;
    }

    public void setUseOfFunds(List<String> useOfFunds) {
        this.useOfFunds = useOfFunds;
    }

    public Double[] getMoney() {
        return money;
    }

    public void setMoney(Double[] money) {
        this.money = money;
    }

    public @Size(min = 2, max = 2) LocalDate[] getTime() {
        return time;
    }

    public void setTime(@Size(min = 2, max = 2) LocalDate[] time) {
        this.time = time;
    }

    public Double[] getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double[] interestRate) {
        this.interestRate = interestRate;
    }

    public Integer[] getRepaymentDuration() {
        return repaymentDuration;
    }

    public void setRepaymentDuration(Integer[] repaymentDuration) {
        this.repaymentDuration = repaymentDuration;
    }

    public CreditRating[] getUserCreditRating() {
        return userCreditRating;
    }

    public void setUserCreditRating(CreditRating[] userCreditRating) {
        this.userCreditRating = userCreditRating;
    }

}
