package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.SelfInfoEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/10/27
 */
public interface SelfInfoEvidenceRepository extends CrudRepository<SelfInfoEvidence, Long> {
    List<SelfInfoEvidence> findDistinctByItem(UserInfoCheckRecord checkRecord);
}
