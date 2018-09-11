package nju.trust.service.verify;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public class VerifyServiceImpl implements VerifyService {

    private UserRepository userRepository;
    @Autowired
    public VerifyServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
