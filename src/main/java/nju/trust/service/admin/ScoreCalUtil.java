package nju.trust.service.admin;

import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 许杨
 * @Description: 个人管理时
 * @Date: 2018/9/3
 */
public class ScoreCalUtil {

    @Autowired
    private UserEvidenceRepository userEvidenceRepository;

    private UserInfoCheckRecord checkRecord;

    public ScoreCalUtil(UserInfoCheckRecord checkRecord) {
        this.checkRecord = checkRecord;
    }

    // 计算得分并在后台更新
    public void calScore() {
        switch (checkRecord.getCheckItem()) {
            case VOLUNTEERTIME:
                calVolunteerScore(checkRecord.getId());
                break;
        }
    }
    // 计算每年平均志愿活动时长加分
    private void calVolunteerScore(Long id) {
        double time = userEvidenceRepository.getVolunteerTime(id);
        double score = time * 1.2;

    }
}
