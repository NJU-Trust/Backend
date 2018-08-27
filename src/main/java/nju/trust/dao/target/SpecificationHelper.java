package nju.trust.dao.target;

import nju.trust.entity.target.SmallTarget;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public class SpecificationHelper {
    static void addDoubleRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                               String name, Double lowerBound, Double higherBound) {
        Expression<Double> expression = root.get(name).as(Double.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    static void addIntegerRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                                String name, Integer lowerBound, Integer higherBound) {
        Expression<Integer> expression = root.get(name).as(Integer.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }

    static void addTimeRange(Root<SmallTarget> root, List<Predicate> predicates, CriteriaBuilder builder,
                             LocalDateTime lowerBound, LocalDateTime higherBound) {
        Expression<LocalDateTime> expression = root.get("startTime").as(LocalDateTime.class);
        if (lowerBound != null)
            predicates.add(builder.greaterThanOrEqualTo(expression, lowerBound));
        if (higherBound != null)
            predicates.add(builder.lessThanOrEqualTo(expression, higherBound));
    }
}
