package nju.trust.dao.target;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.SmallTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
public interface SmallTargetRepository extends JpaRepository<SmallTarget, Long>, JpaSpecificationExecutor<SmallTarget> {

    @Query("select u from SmallTarget u where u.name like %?1%")
    List<SmallTarget> findByNameLike(String name);


}
