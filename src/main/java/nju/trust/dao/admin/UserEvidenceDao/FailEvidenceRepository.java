package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.FailRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface FailEvidenceRepository extends CrudRepository<FailRecord, Long> {
}
