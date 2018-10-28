package nju.trust.dao.admin;

import nju.trust.entity.CheckItem;
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
    List<UserInfoCheckRecord> findByUserUsername(String username);

    List<UserInfoCheckRecord> findByCheckState(CheckState update);

    List<UserInfoCheckRecord> findDistinctByUserUsernameAndAndCheckItem(String username, CheckItem item);

    List<UserInfoCheckRecord> findDistinctByUserUsernameAndCheckItemAndCheckState(String username, CheckItem item, CheckState state);
}
