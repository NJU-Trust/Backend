package nju.trust.dao.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.SmallProjectClassification;
import nju.trust.entity.target.SmallTarget;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public class SmallTargetSpecification implements Specification<SmallTarget> {

    private SmallTargetFilterRequest filter;

    public SmallTargetSpecification(SmallTargetFilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<SmallTarget> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        // Money
        Expression<Double> moneyExpression = root.get("money");
        Optional.ofNullable(filter.getMoney()[0])
                .ifPresent(t -> builder.ge(moneyExpression, t));
        Optional.ofNullable(filter.getMoney()[1])
                .ifPresent(t -> builder.le(moneyExpression, t));

        // Interest rate
        Expression<Double> interestRateExpression = root.get("repayment").get("interestRate");
        Optional.ofNullable(filter.getInterestRate()[0])
                .ifPresent(t -> predicates.add(builder.ge(interestRateExpression, t)));
        Optional.ofNullable(filter.getInterestRate()[1])
                .ifPresent(t -> predicates.add(builder.le(interestRateExpression, t)));

        // Repayment duration
        Expression<Integer> durationExpression = root.get("repayment").get("duration");
        Optional.ofNullable(filter.getRepaymentDuration()[0])
                .ifPresent(t -> predicates.add(builder.ge(durationExpression, t)));
        Optional.ofNullable(filter.getRepaymentDuration()[1])
                .ifPresent(t -> predicates.add(builder.le(durationExpression, t)));

        // Start time
        Expression<LocalDateTime> timeExpression = root.get("startTime");
        Optional.ofNullable(filter.getTime()[0])
                .ifPresent(t -> predicates.add(builder.greaterThanOrEqualTo(timeExpression, t)));
        Optional.ofNullable(filter.getTime()[1])
                .ifPresent(t -> predicates.add(builder.lessThanOrEqualTo(timeExpression, t)));

        Predicate[] p = new Predicate[predicates.size()];
        Predicate result = builder.and(predicates.toArray(p));

        for (CreditRating creditRating : filter.getUserCreditRating())
            result = builder.or(result, builder.equal(root.get("user").get("creditRating"), creditRating));
//        for (SmallProjectClassification classification : filter.getClassifications())
//            result = builder.or(result, builder.equal(root.get("classification"), classification));
        for (String usage : filter.getUseOfFunds())
            result = builder.or(result, builder.equal(root.get("useOfFunds"), usage));

        return result;

    }
}
