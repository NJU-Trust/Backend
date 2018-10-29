package nju.trust.dao.record;

import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.BaseTarget;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public interface InvestmentRecordRepository extends CrudRepository<InvestmentRecord, Long> {
    List<InvestmentRecord> findAllByTargetId(Long targetId);

    List<InvestmentRecord> findAllByUserUsername(String username);

    List<InvestmentRecord> findDistinctByUserUsername(String username);

    List<InvestmentRecord> findDistinctByTimeBetween(LocalDateTime time1, LocalDateTime time2);

    List<InvestmentRecord> findDistinctByUserUsernameAndTarget(String username, BaseTarget target);
}
