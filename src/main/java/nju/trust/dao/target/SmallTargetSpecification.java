package nju.trust.dao.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.target.SmallTarget;
import nju.trust.entity.target.TargetRating;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public class SmallTargetSpecification implements Specification<SmallTarget> {

    private static final Logger log = LoggerFactory.getLogger("SmallTargetSpecification");

    private SmallTargetFilterRequest filter;

    public SmallTargetSpecification(SmallTargetFilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<SmallTarget> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        log.info(filter.toString());
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
        Expression<LocalDate> timeExpression = root.get("startTime");
        Optional.ofNullable(filter.getTime()[0])
                .ifPresent(t -> predicates.add(builder.greaterThanOrEqualTo(timeExpression, t)));
        Optional.ofNullable(filter.getTime()[1])
                .ifPresent(t -> predicates.add(builder.lessThanOrEqualTo(timeExpression, t)));

        Predicate result = builder.and(toPredicateArray(predicates));

        // CreditRating constraints
        if (filter.getUserCreditRating().length != 0) {
            predicates.clear();
            for (CreditRating creditRating : filter.getUserCreditRating()) {
                predicates.add(builder.equal(root.get("user").get("creditRating"), creditRating));
            }
            result = builder.and(result, builder.or(toPredicateArray(predicates)));
        }

        // Use of funds constraints
        if (!filter.getUseOfFunds().isEmpty()) {
            predicates.clear();
            for (String usage : filter.getUseOfFunds())
                predicates.add(builder.equal(root.get("useOfFunds"), usage));
            result = builder.and(result, builder.or(toPredicateArray(predicates)));
        }

        // TargetRating constraints
        if (!filter.getTargetRating().isEmpty()) {
            predicates.clear();
            for (TargetRating targetRating : filter.getTargetRating())
                predicates.add(builder.equal(root.get("targetRating"), targetRating));
            result = builder.and(result, builder.or(toPredicateArray(predicates)));
        }
        return result;
    }

    private Predicate[] toPredicateArray(List<Predicate> predicates) {
        Predicate[] p = new Predicate[predicates.size()];
        return predicates.toArray(p);
    }
}
