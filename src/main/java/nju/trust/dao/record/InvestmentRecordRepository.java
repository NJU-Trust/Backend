package nju.trust.dao.record;

import nju.trust.entity.record.InvestmentRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: J.D. Liao
 * Date: 2018/8/25
 * Description:
 */
public interface InvestmentRecordRepository extends CrudRepository<InvestmentRecord, Long> {
}
