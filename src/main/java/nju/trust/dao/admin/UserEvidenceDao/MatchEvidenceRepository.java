package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.MatchEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface MatchEvidenceRepository extends CrudRepository<MatchEvidence, Long> {
    List<MatchEvidence> findByItemId(Long id);
}
