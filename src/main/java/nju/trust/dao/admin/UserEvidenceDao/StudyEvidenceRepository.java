package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.StudyEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface StudyEvidenceRepository extends CrudRepository<StudyEvidence, Long> {
    List<StudyEvidence> findByItemId(Long id);

    List<StudyEvidence> findDistinctByUserUsername(String username);
}
