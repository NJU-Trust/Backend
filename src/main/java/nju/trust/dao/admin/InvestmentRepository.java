package nju.trust.dao.admin;

import nju.trust.entity.target.Investment;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description: 管理员在用户管理时查询
 * @Date: 2018/8/29
 */
public interface InvestmentRepository extends CrudRepository<Investment, String> {
}
