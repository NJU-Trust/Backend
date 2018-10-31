package nju.trust.payloads.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetType;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public class TargetFilter {

    private String username;

    private Double moneyUpper;

    private Double moneyLower;

    private TargetType targetType;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    public java.util.function.Predicate<BaseTarget> toPredicate() {
        java.util.function.Predicate<BaseTarget> result =
                t -> Optional.ofNullable(moneyLower)
                        .map(m -> t.getMoney() >= m).orElse(true);
        result = result.and(t -> Optional.ofNullable(targetType)
                .map(m -> m == t.getTargetType()).orElse(true))
                .and(t -> Optional.ofNullable(moneyUpper)
                        .map(m -> m >= t.getMoney()).orElse(true))
                .and(t -> Optional.ofNullable(startDate)
                        .map(d -> d.isBefore(t.getStartTime())).orElse(true))
                .and(t -> Optional.ofNullable(endDate)
                        .map(d -> d.isAfter(t.getStartTime())).orElse(true))
                .and(t -> Optional.ofNullable(name)
                        .map(n -> n.equals(t.getName())).orElse(true));

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}