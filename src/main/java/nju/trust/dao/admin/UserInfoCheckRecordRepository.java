package nju.trust.dao.admin;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/1
 */
public interface UserInfoCheckRecordRepository extends CrudRepository<UserInfoCheckRecord, Long> {
    List<UserInfoCheckRecord> findByCheckStateOrCheckState(CheckState checkState1, CheckState checkState2);
}
