package nju.trust.dao.target;

import nju.trust.entity.target.LargeTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public interface LargeTargetRepository extends JpaRepository<LargeTarget, Long>,
        JpaSpecificationExecutor<LargeTarget> {

}
