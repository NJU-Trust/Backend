package nju.trust.service.verify;

import nju.trust.dao.admin.UserEvidenceDao.*;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserEvidence.*;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    private static final CheckState STATE = CheckState.ONGING;

    private UserRepository userRepository;
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    private FailEvidenceRepository failEvidenceRepository;
    private MatchEvidenceRepository matchEvidenceRepository;
    private ScholarshipEvidenceRepository scholarshipEvidenceRepository;
    private VolunteerEvidenceRepository volunteerEvidenceRepository;
    private RewardEvidenceRepository rewardEvidenceRepository;
    @Autowired
    public VerifyServiceImpl(UserRepository userRepository, UserInfoCheckRecordRepository userInfoCheckRecordRepository, FailEvidenceRepository failEvidenceRepository, MatchEvidenceRepository matchEvidenceRepository, ScholarshipEvidenceRepository scholarshipEvidenceRepository, VolunteerEvidenceRepository volunteerEvidenceRepository, RewardEvidenceRepository rewardEvidenceRepository) {
        this.userRepository = userRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
        this.failEvidenceRepository = failEvidenceRepository;
        this.matchEvidenceRepository = matchEvidenceRepository;
        this.scholarshipEvidenceRepository = scholarshipEvidenceRepository;
        this.volunteerEvidenceRepository = volunteerEvidenceRepository;
        this.rewardEvidenceRepository = rewardEvidenceRepository;
    }

    /**
     * 保存校园验证
     * @param info 校园验证的基本信息
     * @param username 用户名
     */
    @Override
    public ApiResponse schoolVerify(SchoolVerifyInfo info, String username) {
        if(!userRepository.existsByUsername(username)) {
            return new ApiResponse(false, "该用户不存在");
        }
        if(info == null || info.hasNull()) {
            return new ApiResponse(false, "填写信息不完整");
        }
        User user = userRepository.findByUsername(username).get();
        user.setRealName(info.getRealName());
        user.setGender(info.getGender());
        user.setBirthday(info.getBirthday());
        user.setIdCardNumber(info.getIdCardNumber());
        user.setUniversity(info.getUniversity());
        user.setInstitution(info.getInstitution());
        user.setMajor(info.getMajor());
        user.setAlipay(info.getAlipay());
        user.setStuCardImage(info.getStuCardImage());
        user.setSchoolCardImage(info.getSchoolCardImage());
        userRepository.save(user);
        return ApiResponse.successResponse();
    }

    /**
     * 保存个性信息
     * @param username 用户名
     * @param fail 挂科数（挂科数）
     * @param report_cards 成绩报告单
     * @param school_rewards 校级奖励
     * @param city_rewards 市级奖励
     * @param province_rewards 省级奖励
     * @param country_rewards 国家级奖励
     * @param volunteer 志愿时长
     * @param volunteer_img 志愿时长截图
     * @param self_qualifications 个人证书
     */
    @Override
    public ApiResponse selfInfoVerify(String username, int fail, List<String> report_cards, List<NameAndEvidence> school_rewards,
                                      List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards,
                                      List<NameAndEvidence> country_rewards, double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications) {
        if(!userRepository.existsByUsername(username)) {
            return new ApiResponse(false, "该用户不存在");
        }
        User user = userRepository.findByUsername(username).get();
        LocalDateTime time = LocalDateTime.now();

        // 挂科数  fail report_cards
        saveFailure(user, fail, report_cards, time);
        // 获校级奖情况 school_rewards city_rewards province_rewards country_rewards
        saveRewards(user, school_rewards, time, BonusType.SCHOOL);
        saveRewards(user, city_rewards, time, BonusType.CITY);
        saveRewards(user, province_rewards, time, BonusType.PROVINCE);
        saveRewards(user, country_rewards, time, BonusType.NATION);
        // 志愿时长 volunteer volunteer_img
        saveVolunteer(user, volunteer, volunteer_img, time);
        // 获奖情况的执业证书 self_qualifications
        if(self_qualifications != null && self_qualifications.size() > 0) {
            for (NameAndEvidence detail : self_qualifications) {
                saveQualification(user, detail, time);
            }
        }

        return ApiResponse.successResponse();
    }
    // 获奖情况的执业证书
    private void saveQualification(User user, NameAndEvidence detail, LocalDateTime time) {
        String qualificationName = detail.getValue();
        String evidence = detail.getValue();
        UserInfoCheckRecord item = getUserInfoCheckRecord(user, CheckItem.REWARD, qualificationName);
        RewardEvidence rewardEvidence = new RewardEvidence(user, item, time, STATE, evidence, RewardType.CERTIFICATE);
        rewardEvidenceRepository.save(rewardEvidence);
    }

    // 志愿时长
    private void saveVolunteer(User user, double volunteer, String volunteer_img, LocalDateTime time) {
        UserInfoCheckRecord item = getUserInfoCheckRecord(user, CheckItem.VOLUNTEERTIME, volunteer+"小时");
        VolunteerEvidence volunteerEvidence = new VolunteerEvidence(user, item, time, STATE, volunteer_img, volunteer);
        volunteerEvidenceRepository.save(volunteerEvidence);
    }
    // 挂科数目
    private void saveFailure(User user, int fail, List<String> report_cards, LocalDateTime time) {
        if (report_cards == null || report_cards.size() <= 0) {
            return;
        }

        UserInfoCheckRecord item = getUserInfoCheckRecord(user, CheckItem.FAILNUM, null);

        List<FailEvidence> failEvidences = new ArrayList<>();
        for(String evidence : report_cards) {
            FailEvidence failEvidence = new FailEvidence(user, item, time, STATE, evidence, fail);
            failEvidences.add(failEvidence);
        }

        failEvidenceRepository.saveAll(failEvidences);
    }
    // 校级获奖情况
    private void saveRewards(User user, List<NameAndEvidence> records, LocalDateTime time, BonusType bonusType) {
        if(records != null && records.size() > 0) {
            for(NameAndEvidence detail : records) {
                saveReward(user, detail, time, bonusType);
            }
        }
    }
    private void saveReward(User user, NameAndEvidence detail, LocalDateTime time, BonusType bonusType) {
        String reward = detail.getValue();
        String evidence = detail.getFile();

        // 奖学金
        if(reward.contains("奖学金")) {
            saveScholarShip(user, reward, evidence, bonusType, time);
        }else { // 竞赛获奖
            saveMatch(user, reward, evidence, bonusType, time);
        }
    }
    // 奖学金
    private void saveScholarShip(User user, String reward, String evidence, BonusType type, LocalDateTime time) {
        UserInfoCheckRecord item = getUserInfoCheckRecord(user, CheckItem.SCHOLARSHIP, reward);
        ScholarshipEvidence scholarshipEvidence = new ScholarshipEvidence(user, item, time, STATE, evidence);
        scholarshipEvidence.setType(type);
        scholarshipEvidenceRepository.save(scholarshipEvidence);
    }
    // 竞赛获奖
    private void saveMatch(User user, String reward, String evidence, BonusType type, LocalDateTime time) {
        UserInfoCheckRecord item = getUserInfoCheckRecord(user, CheckItem.MATCH, reward);
        MatchEvidence matchEvidence = new MatchEvidence(user, item, time, STATE, evidence);
        matchEvidence.setType(type);
        matchEvidenceRepository.save(matchEvidence);
    }

    private UserInfoCheckRecord getUserInfoCheckRecord(User user, CheckItem checkItem, String description) {
        UserInfoCheckRecord userInfoCheckRecord = new UserInfoCheckRecord();

        userInfoCheckRecord.setCheckItem(checkItem);
        userInfoCheckRecord.setUser(user);
        userInfoCheckRecord.setCheckState(STATE);
        userInfoCheckRecord.setDescription(description);

        userInfoCheckRecordRepository.save(userInfoCheckRecord);

        return userInfoCheckRecord;
    }
}
