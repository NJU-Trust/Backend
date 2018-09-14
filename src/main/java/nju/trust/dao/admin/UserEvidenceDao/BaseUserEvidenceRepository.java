package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.BaseUserEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 个人信息的凭证
 * @Date: 2018/9/8
 */
public interface BaseUserEvidenceRepository extends CrudRepository<BaseUserEvidence, Long> {
    List<BaseUserEvidence> findEvidencesByItem(UserInfoCheckRecord item);

    List<BaseUserEvidence> findByItem(UserInfoCheckRecord item);

    List<BaseUserEvidence> findByItemId(Long itemId);
}
