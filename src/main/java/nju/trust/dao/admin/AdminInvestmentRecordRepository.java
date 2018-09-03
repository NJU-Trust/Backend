package nju.trust.dao.admin;

import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员查询投资记录
 * @Date: 2018/8/31
 */
public interface AdminInvestmentRecordRepository extends CrudRepository<InvestmentRecord, Long> {
    List<User> findUserById(Long id);
}
