package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.FailEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface FailEvidenceRepository extends CrudRepository<FailEvidence, Long> {
    List<FailEvidence> findByItemId(Long id);

    List<FailEvidence> findDistinctByUserUsername(String username);
}
