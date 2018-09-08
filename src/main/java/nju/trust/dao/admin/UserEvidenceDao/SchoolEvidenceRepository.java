package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.SchoolEvidence;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface SchoolEvidenceRepository extends CrudRepository<SchoolEvidence, Long> {
}
