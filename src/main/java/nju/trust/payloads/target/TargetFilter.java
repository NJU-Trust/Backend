package nju.trust.payloads.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public class TargetFilter {

    private Double moneyUpper;

    private Double moneyLower;

    private TargetType targetType;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    public Specification<BaseTarget> toSpecification(String username) {
        return (Specification<BaseTarget>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("user").get("username"), username));
            Optional.ofNullable(moneyLower)
                    .ifPresent(t -> predicates.add(criteriaBuilder.ge(root.get("money"), t)));
            Optional.ofNullable(moneyUpper)
                    .ifPresent(t -> predicates.add(criteriaBuilder.le(root.get("money"), t)));
            Optional.ofNullable(targetType)
                    .ifPresent(t -> predicates.add(criteriaBuilder.equal(root.get("targetType"), t)));
            Optional.ofNullable(name)
                    .ifPresent(t -> predicates.add(criteriaBuilder.like(root.get("name"), t)));
            Optional.ofNullable(startDate)
                    .ifPresent(t -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), t)));
            Optional.ofNullable(endDate)
                    .ifPresent(t -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startTime"), t)));

            Predicate[] result = new Predicate[predicates.size()];
            return criteriaBuilder.and(result);
        };
    }

    public java.util.function.Predicate<BaseTarget> toPredicate() {
        java.util.function.Predicate<BaseTarget> result =
                t -> Optional.ofNullable(t.getMoney())
                        .map(m -> m <= moneyUpper && m >= moneyLower).orElse(true);
        result = result.and(t -> Optional.ofNullable(t.getTargetType()).map(m -> m == targetType).orElse(true))
                .and(t -> Optional.ofNullable(t.getStartTime())
                        .map(d -> d.isBefore(endDate) && d.isAfter(startDate)).orElse(true));

        return result;
    }

    public void setMoneyUpper(Double moneyUpper) {
        this.moneyUpper = moneyUpper;
    }

    public void setMoneyLower(Double moneyLower) {
        this.moneyLower = moneyLower;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}