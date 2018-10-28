package nju.trust.service.verify;

import nju.trust.dao.admin.UserEvidenceDao.*;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserEvidence.*;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.Gender;
import nju.trust.entity.user.RoleName;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private SelfInfoEvidenceRepository selfInfoEvidenceRepository;
    @Autowired
    public VerifyServiceImpl(UserRepository userRepository, UserInfoCheckRecordRepository userInfoCheckRecordRepository, FailEvidenceRepository failEvidenceRepository, MatchEvidenceRepository matchEvidenceRepository, ScholarshipEvidenceRepository scholarshipEvidenceRepository, VolunteerEvidenceRepository volunteerEvidenceRepository, RewardEvidenceRepository rewardEvidenceRepository, SelfInfoEvidenceRepository selfInfoEvidenceRepository) {
        this.userRepository = userRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
        this.failEvidenceRepository = failEvidenceRepository;
        this.matchEvidenceRepository = matchEvidenceRepository;
        this.scholarshipEvidenceRepository = scholarshipEvidenceRepository;
        this.volunteerEvidenceRepository = volunteerEvidenceRepository;
        this.rewardEvidenceRepository = rewardEvidenceRepository;
        this.selfInfoEvidenceRepository = selfInfoEvidenceRepository;
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
        userRepository.save(user);

        LocalDateTime time = LocalDateTime.now();
        CheckState state = CheckState.ONGING;
        UserInfoCheckRecord item = new UserInfoCheckRecord();
        item.setCheckItem(CheckItem.SELFINFO);
        item.setUser(user);
        item.setCheckState(state);
        item.setTime(time);
        item.setDescription(user.getRealName());
        userInfoCheckRecordRepository.save(item);

        SelfInfoEvidence stuCardEvidence = new SelfInfoEvidence(user, item, time, state, info.getStuCardImage(), SelfInfoEvidenceType.STUDENTCARD, SelfInfoType.STUDENT);
        selfInfoEvidenceRepository.save(stuCardEvidence);
        SelfInfoEvidence schoolCardEvidence = new SelfInfoEvidence(user, item, time, state, info.getSchoolCardImage(), SelfInfoEvidenceType.SCHOOLCARD, SelfInfoType.STUDENT);
        selfInfoEvidenceRepository.save(schoolCardEvidence);

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

    /**
     * 校友信息验证
     * @param username     用户名
     * @param gender       性别（男 女）
     * @param birthday     出生年月日（2018-01-01）
     * @param idCardNumber 身份证号
     * @param education    学历（本科毕业、研究生毕业、博士毕业）
     * @param evidence     学历证明
     * @param institution  工作单位
     * @param livingPlace  居住地
     * @return 提交是否成功
     */
    @Override
    public ApiResponse alumnaVerify(String username, String gender, String birthday, String idCardNumber, String education, String evidence, String institution, String livingPlace) {
        // 性别（男 女）
        Gender genderEnum = Gender.getGender(gender);
        if(genderEnum == null) {
            return new ApiResponse(false, "性别填写错误");
        }
        // 出生年月日（2018-01-01）
        LocalDate birth = LocalDate.parse(birthday.split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(birth.isAfter(LocalDate.now())) {
            return new ApiResponse(false, "生日填写错误");
        }

        // 身份证

        if(!userRepository.existsByUsername(username)) {
            return new ApiResponse(false, "用户不存在");
        }
        User user = userRepository.findByUsername(username).get();
        user.setGender(genderEnum);
        user.setBirthday(birth);
        user.setIdCardNumber(idCardNumber);
        user.setInstitution(institution);
        user.setLivingPlace(livingPlace);
        userRepository.save(user);

        // 学历 学历证明
        LocalDateTime time = LocalDateTime.now();
        CheckState state = CheckState.ONGING;
        UserInfoCheckRecord item = new UserInfoCheckRecord();
        item.setCheckItem(CheckItem.SELFINFO);
        item.setUser(user);
        item.setCheckState(state);
        item.setTime(time);
        item.setDescription(education);
        userInfoCheckRecordRepository.save(item);

        SelfInfoEvidence educationEvidence = new SelfInfoEvidence(user, item, time, state, evidence, SelfInfoEvidenceType.EDUCATION, SelfInfoType.ALUMNA);
        selfInfoEvidenceRepository.save(educationEvidence);

        return ApiResponse.successResponse();
    }

    /**
     * 得到用户角色
     * @param username 用户名
     * @return ["初级/非初级/初级审核中","校友/学生"]
     */
    @Override
    public List<String> getRoles(String username) {
        List<String> roles = new ArrayList<>();

        User user = userRepository.findByUsername(username).get();
        Set<RoleName> roleNameSet = user.getRoles();

        if(roleNameSet.contains(RoleName.ROLE_PRIMARY)) {
            // 区分初级、初级审核中
            List<UserInfoCheckRecord> ongoingList = userInfoCheckRecordRepository.findDistinctByUserUsernameAndCheckItemAndCheckState(username, CheckItem.SELFINFO, CheckState.ONGING);
            List<UserInfoCheckRecord> rejectList = userInfoCheckRecordRepository.findDistinctByUserUsernameAndCheckItemAndCheckState(username, CheckItem.SELFINFO, CheckState.REJECT);
            List<UserInfoCheckRecord> updateList = userInfoCheckRecordRepository.findDistinctByUserUsernameAndCheckItemAndCheckState(username, CheckItem.SELFINFO, CheckState.UPDATE);

            if(ongoingList.size() == 0 && rejectList.size() == 0 && updateList.size() == 0) {
                roles.add("初级");
            }else {
                roles.add("初级审核中");
            }
        }else {
            roles.add("非初级");
        }

        if(roleNameSet.contains(RoleName.ROLE_SF)) {
            roles.add("校友");
        }else {
            roles.add("学生");
        }

        return roles;
    }
}
