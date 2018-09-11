package nju.trust.dao.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
public interface TargetRepository
        extends PagingAndSortingRepository<BaseTarget, Long>, JpaSpecificationExecutor<BaseTarget> {

    List<BaseTarget> findByTargetState(TargetState targetState, Pageable pageable);

    List<BaseTarget> findByTargetTypeAndTargetState(TargetType targetType, TargetState targetState, Pageable pageable);
}