package nju.trust.dao.admin;

import nju.trust.entity.record.UserEvidence.BaseUserEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/2
 */
public interface UserEvidenceRecordRepository extends CrudRepository<BaseUserEvidence, Long> {
    List<String> findEvidencesByItem(UserInfoCheckRecord item);

    List<BaseUserEvidence> findByItem(UserInfoCheckRecord item);
}
