package nju.trust.dao.record;

import nju.trust.entity.record.InvestmentRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public interface InvestmentRecordRepository extends CrudRepository<InvestmentRecord, Long> {
    List<InvestmentRecord> findAllByTargetId(Long targetId);

    List<InvestmentRecord> findAllByUserUsername(String username);
}
