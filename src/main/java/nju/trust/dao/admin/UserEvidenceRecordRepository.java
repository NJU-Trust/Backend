package nju.trust.dao.admin;

import nju.trust.entity.record.UserEvidenceRecord;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/2
 */
public interface UserEvidenceRecordRepository extends CrudRepository<UserEvidenceRecord, Long> {
    List<String> findEvidencesByItem(UserInfoCheckRecord item);

    List<UserEvidenceRecord> findByItem(UserInfoCheckRecord item);
}
