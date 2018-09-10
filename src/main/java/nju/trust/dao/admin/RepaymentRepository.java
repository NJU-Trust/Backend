package nju.trust.dao.admin;

import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/8/30
 */
public interface RepaymentRepository extends CrudRepository<Repayment, Long> {

    /**
     * 查找用户的欠款
     * @param username 用户名
     * @return
     */
    //List<Double> findRemainingAmountByUserUsername(String username);

    /**
     *
     * @param user 用户
     * @return 还款记录
     */
    @Query(value = "select r from Repayment r where r.user = ?1")
    List<Repayment> getRepaymentByUser(User user);

    List<Repayment> findAllByUserUsername(String username);

    boolean existsByUser(User user);

    boolean existsByTargetId(Long id);

    Repayment findFirstByTargetId(Long id);
}
