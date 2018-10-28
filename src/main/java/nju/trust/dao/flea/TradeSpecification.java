package nju.trust.dao.flea;

import nju.trust.entity.flea.Trade;
import nju.trust.payloads.flea.TradeFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/20
 * @Todo:
 */
public class TradeSpecification implements Specification<Trade> {
    private TradeFilterRequest filter;

    public TradeSpecification(TradeFilterRequest tradeFilterRequest) {
        this.filter = tradeFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Trade> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate[] p = new Predicate[predicates.size()];
        Predicate result = criteriaBuilder.and(predicates.toArray(p));
        if (!filter.getGoodsTypes().isEmpty()) {
            predicates.clear();
            for (String types : filter.getGoodsTypes())
                predicates.add(criteriaBuilder.equal(root.get("goodsType"), types));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        if (!filter.getGoodsName().equals("")) {
            predicates.clear();
            predicates.add(criteriaBuilder.like(root.get("goodsName"), "%" + filter.getGoodsName() + "%"));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        if (!filter.isMine()){
            predicates.clear();
            System.out.println(filter.isSelling());
            predicates.add(criteriaBuilder.equal(root.get("isSelling"), filter.isSelling()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }


        if (filter.isMine() && filter.isSellingAll()) {
            predicates.clear();
            predicates.add(criteriaBuilder.equal(root.get("fromUsername"), filter.getUsername()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
            predicates.clear();
            predicates.add(criteriaBuilder.equal(root.get("isSelling"), filter.isSelling()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        if (filter.isMine() && !filter.isSellingAll() && !filter.isSelling()) {
            predicates.clear();
            predicates.add(criteriaBuilder.equal(root.get("fromUsername"), filter.getUsername()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
            predicates.clear();
            predicates.add(criteriaBuilder.equal(root.get("isSelling"), filter.isSelling()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
            predicates.clear();
            predicates.add(criteriaBuilder.equal(root.get("isRating"), filter.isRating()));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        return result;
    }
    private Predicate[] toPredicateArray(List<Predicate> predicates) {
        Predicate[] p = new Predicate[predicates.size()];
        return predicates.toArray(p);
    }
}
