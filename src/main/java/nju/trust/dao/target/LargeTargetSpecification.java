package nju.trust.dao.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.LargeProjectClassification;
import nju.trust.entity.target.LargeTarget;
import nju.trust.entity.user.User;
import nju.trust.payloads.target.LargeTargetFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class LargeTargetSpecification implements Specification<LargeTarget> {

    private LargeTargetFilterRequest filter;

    public LargeTargetSpecification(LargeTargetFilterRequest filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<LargeTarget> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        addDoubleRange(root, predicates, builder, "money", filter.getMoney()[0], filter.getMoney()[1]);
        addDoubleRange(root, predicates, builder, "interestRate",
                filter.getInterestRate()[0], filter.getInterestRate()[1]);
        addIntegerRange(root, predicates, builder,
                filter.getRepaymentDuration()[0], filter.getRepaymentDuration()[1]);
        addTimeRange(root, predicates, builder, filter.getTime()[0], filter.getTime()[1]);

        Join<LargeTarget, User> userJoin = root.join("user");
        addIntegerRange(userJoin, predicates, builder,
                filter.getUserFailedSubject()[0], filter.getUserFailedSubject()[1]);
        addDoubleRange(userJoin, predicates, builder,
                filter.getUserRankingRate()[0], filter.getUserRankingRate()[1]);

        Predicate[] p = new Predicate[predicates.size()];
        Predicate result = builder.and(predicates.toArray(p));
        for (CreditRating creditRating : filter.getUserCreditRating())
            result = builder.or(result, builder.equal(userJoin.get("creditRating"), creditRating));
        for (LargeProjectClassification classification : filter.getClassifications())
            result = builder.or(result, builder.equal(root.get("classification"), classification));

        return result;
    }

    private void addDoubleRange(Root<LargeTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                               String name, Double lowerBound, Double higherBound) {
        Expression<Double> expression = root.get(name).as(Double.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addIntegerRange(Root<LargeTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                                 Integer lowerBound, Integer higherBound) {
        Expression<Integer> expression = root.get("repaymentDuration").as(Integer.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addTimeRange(Root<LargeTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                             LocalDateTime lowerBound, LocalDateTime higherBound) {
        Expression<LocalDateTime> expression = root.get("startTime").as(LocalDateTime.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addIntegerRange(Join<LargeTarget, User> root, List<Predicate> predicates, CriteriaBuilder builder,
                                 Integer lowerBound, Integer higherBound) {
        Expression<Integer> expression = root.get("failedSubjects").as(Integer.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addDoubleRange(Join<LargeTarget, User> root, List<Predicate> predicates, CriteriaBuilder builder,
                                Double lowerBound, Double higherBound) {
        Expression<Double> expression = root.get("rankingRate").as(Double.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }
}
