package nju.trust.dao;

import nju.trust.entity.target.BaseTarget;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
public interface TargetRepository extends PagingAndSortingRepository<BaseTarget, Long> {

}