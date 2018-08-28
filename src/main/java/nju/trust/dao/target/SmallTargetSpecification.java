package nju.trust.dao.target;

import nju.trust.entity.CreditRating;
import nju.trust.entity.SmallProjectClassification;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.SmallTarget;
import nju.trust.entity.user.User;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        // Money range
        addDoubleRange(root, predicates, builder, "money", filter.getMoney()[0], filter.getMoney()[1]);
        addDoubleRange(root, predicates, builder, "interestRate",
                filter.getInterestRate()[0], filter.getInterestRate()[1]);
        addIntegerRange(root, predicates, builder, "repaymentDuration",
                filter.getRepaymentDuration()[0], filter.getRepaymentDuration()[1]);
        addTimeRange(root, predicates, builder, filter.getTime()[0], filter.getTime()[1]);

        Predicate[] p = new Predicate[predicates.size()];
        Predicate result = builder.and(predicates.toArray(p));

        Join<BaseTarget, User> userJoin = root.join("user");
        for (CreditRating creditRating : filter.getUserCreditRating())
            result = builder.or(result, builder.equal(userJoin.get("creditRating"), creditRating));
        for (SmallProjectClassification classification : filter.getClassifications())
            result = builder.or(result, builder.equal(root.get("classification"), classification));

        return result;

    }

    private void addDoubleRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                               String name, Double lowerBound, Double higherBound) {
        Expression<Double> expression = root.get(name).as(Double.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addIntegerRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                                String name, Integer lowerBound, Integer higherBound) {
        Expression<Integer> expression = root.get(name).as(Integer.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    private void addTimeRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                             LocalDateTime lowerBound, LocalDateTime higherBound) {
        Expression<LocalDateTime> expression = root.get("startTime").as(LocalDateTime.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }
}
