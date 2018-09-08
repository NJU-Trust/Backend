package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.VolunteerRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface VolunteerRecordRepository extends CrudRepository<VolunteerRecord, Long> {
}
