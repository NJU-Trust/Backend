package nju.trust.service.admin;

import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 许杨
 * @Description: 个人管理时
 * @Date: 2018/9/3
 */
@Service
public class ScoreCalUtil {

    @Autowired
    private UserEvidenceRepository userEvidenceRepository;
    @Autowired
    private UnstructuredDataRepository unstructuredDataRepository;

    // 计算得分并在后台更新
    public void calScore(UserInfoCheckRecord checkRecord) {
        Long id = checkRecord.getId();
        String username = checkRecord.getUser().getUsername();

        System.out.println("id:"+id+"  user:"+username);

        switch (checkRecord.getCheckItem()) {
            case VOLUNTEERTIME:
                calVolunteerScore(id, username);
                break;
            case STUDENTWORK:
                calStudentWorkScore(id);
                break;
            case REWARD:
                calRewardScore(id);
                break;
            case MATCH:
                calMatchScore(id);
                break;
            case SCHOLARSHIP:
                calScholarshipScore(id);
                break;
            case SCHOOLTYPE:
                calSchoolTypeScore(id);
                break;
            case MAJOR:
                calMajorTypeScore(id);
                break;
            case EDUCATION:
                calEducationScore(id);
                break;
            case FAILNUM:
                calFailNumScore(id);
                break;
            case STUDY:
                calStudyScore(id);
                break;
            case DISCREDIT:
                calDiscreditScore(id);
                break;
            case PUNISHMENT:
                calPunishmentScore(id);
                break;
            case TESTCHEAT:
                calTestCheatScore(id);
                break;
            case PAYMENT:
                calPaymentScore(id);
                break;
            case REPAYMENT:
                calRepaymentScore(id);
                break;
            case RETURNBOOKS:
                calReturnBooksScore(id);
                break;
        }
    }

    // 计算每年平均志愿活动时长加分
    private void calVolunteerScore(Long id, String username) {
        double time = userEvidenceRepository.getVolunteerTime(id);
        double addScore = time * 1.2;
        UnstructuredData pre = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, UnstructuredDataType.SOCIALITY);
        double preScore = pre.getScore();
        if(preScore+addScore > 100) {
            pre.setScore(100.0);
        }else {
            pre.setScore(preScore+addScore);
        }
        unstructuredDataRepository.save(pre);
    }
    private void calStudentWorkScore(Long id) {
    }
    private void calRewardScore(Long id) {
    }
    private void calMatchScore(Long id) {
    }
    private void calScholarshipScore(Long id) {
    }
    private void calSchoolTypeScore(Long id) {
    }
    private void calMajorTypeScore(Long id) {
    }
    private void calEducationScore(Long id) {
    }
    private void calFailNumScore(Long id) {
    }
    private void calStudyScore(Long id) {
    }
    private void calDiscreditScore(Long id) {
    }
    private void calPunishmentScore(Long id) {

    }
    private void calTestCheatScore(Long id) {
    }
    private void calPaymentScore(Long id) {
    }
    private void calRepaymentScore(Long id) {
    }
    private void calReturnBooksScore(Long id) {
    }
}
