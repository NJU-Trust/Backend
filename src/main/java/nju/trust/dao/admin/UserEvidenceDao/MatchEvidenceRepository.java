package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.MatchEvidence;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface MatchEvidenceRepository extends CrudRepository<MatchEvidence, Long> {
}
