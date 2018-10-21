package nju.trust.service.verify;

import nju.trust.dao.admin.UserEvidenceDao.FailEvidenceRepository;
import nju.trust.dao.admin.UserEvidenceDao.MatchEvidenceRepository;
import nju.trust.dao.admin.UserEvidenceDao.RewardEvidenceRepository;
import nju.trust.dao.admin.UserEvidenceDao.VolunteerEvidenceRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    private UserRepository userRepository;
    private FailEvidenceRepository failEvidenceRepository;
    private MatchEvidenceRepository matchEvidenceRepository;
    private VolunteerEvidenceRepository volunteerEvidenceRepository;
    private RewardEvidenceRepository rewardEvidenceRepository;


    @Autowired
    public VerifyServiceImpl(UserRepository userRepository, FailEvidenceRepository failEvidenceRepository, MatchEvidenceRepository matchEvidenceRepository, VolunteerEvidenceRepository volunteerEvidenceRepository, RewardEvidenceRepository rewardEvidenceRepository) {
        this.userRepository = userRepository;
        this.failEvidenceRepository = failEvidenceRepository;
        this.matchEvidenceRepository = matchEvidenceRepository;
        this.volunteerEvidenceRepository = volunteerEvidenceRepository;
        this.rewardEvidenceRepository = rewardEvidenceRepository;
    }

    /**
     * 保存校园验证
     * @param info 校园验证的基本信息
     * @param username 用户名
     * @return
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

    @Override
    public ApiResponse selfInfoVerify(String username, int fail, List<String> report_cards, List<NameAndEvidence> school_rewards,
                List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards,
                List<NameAndEvidence> country_rewards, double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications) {
        if(!userRepository.existsByUsername(username)) {
            return new ApiResponse(false, "该用户不存在");
        }
        User user = userRepository.findByUsername(username).get();

        return null;
    }
}
