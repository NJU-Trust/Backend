package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.VolunteerEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface VolunteerEvidenceRepository extends CrudRepository<VolunteerEvidence, Long> {
    List<VolunteerEvidence> findByItemId(Long id);
}
