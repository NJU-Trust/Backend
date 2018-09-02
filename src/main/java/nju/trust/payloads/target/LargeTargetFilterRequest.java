package nju.trust.payloads.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.LargeProjectClassification;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class LargeTargetFilterRequest {

    public static LargeTargetFilterRequest testData() {
        LargeTargetFilterRequest filter = new LargeTargetFilterRequest();
        filter.setInterestRate(new Double[]{0., 0.});
        filter.setMoney(new Double[]{0., 0.});
        filter.setRepaymentDuration(new Integer[]{0, 0});
        filter.setTime(new LocalDateTime[]{LocalDateTime.now(), LocalDateTime.now()});
        filter.setClassifications(new LargeProjectClassification[]{
                LargeProjectClassification.CERTIFICATE_TEST, LargeProjectClassification.GMAT});
        filter.setUserCreditRating(new CreditRating[]{});
        filter.setUserFailedSubject(new Integer[]{0, null});
        filter.setUserRankingRate(new Double[]{null, 50.});

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

    @Size(min = 2, max = 2)
    private Integer[] userFailedSubject;

    @Size(min = 2, max = 2)
    private Double[] userRankingRate;

    private CreditRating[] userCreditRating;

    private LargeProjectClassification[] classifications;

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

    public Integer[] getUserFailedSubject() {
        return userFailedSubject;
    }

    public void setUserFailedSubject(Integer[] userFailedSubject) {
        this.userFailedSubject = userFailedSubject;
    }

    public Double[] getUserRankingRate() {
        return userRankingRate;
    }

    public void setUserRankingRate(Double[] userRankingRate) {
        this.userRankingRate = userRankingRate;
    }

    public CreditRating[] getUserCreditRating() {
        return userCreditRating;
    }

    public void setUserCreditRating(CreditRating[] userCreditRating) {
        this.userCreditRating = userCreditRating;
    }

    public LargeProjectClassification[] getClassifications() {
        return classifications;
    }

    public void setClassifications(LargeProjectClassification[] classifications) {
        this.classifications = classifications;
    }
}
