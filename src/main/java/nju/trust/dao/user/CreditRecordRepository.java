package nju.trust.dao.user;

import nju.trust.entity.user.CreditRecord;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/10/24
 */
public interface CreditRecordRepository extends CrudRepository<CreditRecord, Long> {
    List<CreditRecord> findByUserUsernameAndDateBetween(String username, LocalDate start, LocalDate end);
}
