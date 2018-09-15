package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.SchoolEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface SchoolEvidenceRepository extends CrudRepository<SchoolEvidence, Long> {
    List<SchoolEvidence> findByItemId(Long id);

    List<SchoolEvidence> findDistinctByUserUsername(String username);
}
