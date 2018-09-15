package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.MajorEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface MajorEvidenceRepository extends CrudRepository<MajorEvidence, Long> {
    List<MajorEvidence> findByItemId(Long id);

    List<MajorEvidence> findDistinctByUserUsername(String username);
}
