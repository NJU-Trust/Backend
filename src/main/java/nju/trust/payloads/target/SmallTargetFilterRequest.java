package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.SmallProjectClassification;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
        filter.setTime(new LocalDateTime[]{LocalDateTime.now(), LocalDateTime.now()});
        filter.setClassifications(new SmallProjectClassification[]{});
        filter.setUserCreditRating(new CreditRating[]{});

        return filter;
    }

    @Size(min = 2, max = 2)
    private Double[] money;

    @Size(min = 2, max = 2)
    private LocalDateTime[] time;

    @Size(min = 2, max = 2)
    private Double[] interestRate;

    @Size(min = 2, max = 2)
    private Integer[] repaymentDuration;

    private CreditRating[] userCreditRating;

    private SmallProjectClassification[] classifications;

    public Double[] getMoney() {
        return money;
    }

    public void setMoney(Double[] money) {
        this.money = money;
    }

    public LocalDateTime[] getTime() {
        return time;
    }

    public void setTime(LocalDateTime[] time) {
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

    public SmallProjectClassification[] getClassifications() {
        return classifications;
    }

    public void setClassifications(SmallProjectClassification[] classifications) {
        this.classifications = classifications;
    }
}
