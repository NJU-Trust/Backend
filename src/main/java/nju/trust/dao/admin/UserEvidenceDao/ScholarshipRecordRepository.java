package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.ScholarshipRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface ScholarshipRecordRepository extends CrudRepository<ScholarshipRecord, Long> {
}