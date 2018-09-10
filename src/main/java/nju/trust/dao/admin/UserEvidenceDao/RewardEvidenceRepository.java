package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.RewardEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface RewardEvidenceRepository extends CrudRepository<RewardEvidence, Long> {
    List<RewardEvidence> findByItemId(Long id);
}
