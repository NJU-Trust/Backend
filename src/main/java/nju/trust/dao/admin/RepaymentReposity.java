package nju.trust.dao.admin;

import nju.trust.entity.user.Repayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/8/30
 */
public interface RepaymentReposity extends CrudRepository<Repayment, Long> {

    /**
     * 查找用户的欠款总额
     * @param username 用户名
     * @return
     */
    // TODO user
    @Query(value = "select sum(remainingAmount) from Repayment r where r.user = ?1")
    Double getRemainingAmount(String username);

    /**
     *
     * @param username 用户名
     * @return
     */
    @Query(value = "select * from Repayment r where r.user = ?1")
    List<Repayment> getRepaymentByUsername(String username);
}
