package nju.trust.dao.lostfound;

import nju.trust.entity.lostfound.LostAndFound;
import nju.trust.payloads.lostfound.LostAndFoundFilterRequest;
import nju.trust.payloads.lostfound.LostPlace;
import nju.trust.payloads.lostfound.MsgProperty;
import nju.trust.payloads.lostfound.ThingsType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/22
 * @Todo:
 */
public class LostAndFoundSpecification implements Specification<LostAndFound> {
    private LostAndFoundFilterRequest filter;

    public LostAndFoundSpecification(LostAndFoundFilterRequest lostAndFoundFilterRequest) {
        this.filter = lostAndFoundFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<LostAndFound> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate[] p = new Predicate[predicates.size()];
        Predicate result = criteriaBuilder.and(predicates.toArray(p));
        if (!filter.getThingsTypes().isEmpty()) {
            predicates.clear();
            for (String types : filter.getThingsTypes())
                predicates.add(criteriaBuilder.equal(root.get("thingsType"), ThingsType.getThingsType(types)));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        if (!filter.getLostPlaces().isEmpty()) {
            predicates.clear();
            for (String types : filter.getLostPlaces())
                predicates.add(criteriaBuilder.equal(root.get("lostPlace"), LostPlace.getLostPlace(types)));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        if (!filter.getMsgProperties().isEmpty()) {
            predicates.clear();
            for (String types : filter.getMsgProperties())
                predicates.add(criteriaBuilder.equal(root.get("property"), MsgProperty.getMsgProperty(types)));
            result = criteriaBuilder.and(result, criteriaBuilder.or(toPredicateArray(predicates)));
        }
        return result;
    }
    private Predicate[] toPredicateArray(List<Predicate> predicates) {
        Predicate[] p = new Predicate[predicates.size()];
        return predicates.toArray(p);
    }
}
