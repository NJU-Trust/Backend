package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.DiscreditRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface DiscreditRecordRepository extends CrudRepository<DiscreditRecord, Long> {
}
