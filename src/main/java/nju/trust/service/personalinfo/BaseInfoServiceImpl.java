package nju.trust.service.personalinfo;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.Gender;
import nju.trust.entity.user.User;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.personalinfomation.ChangedPersonalInfo;
import nju.trust.payloads.personalinfomation.PersonalBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/16
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {

    private UserRepository userRepository;

    @Autowired
    public BaseInfoServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public PersonalBaseInfo getBaseInfo(String username) {
        User user;
        if(userRepository.existsByUsername(username)){
            user = userRepository.findByUsername(username).get();
        }else{
            user = userRepository.findByUsername(username).orElseThrow(
                    () -> new ResourceNotFoundException("username not found"));
        }
        PersonalBaseInfo personalBaseInfo = new PersonalBaseInfo(user);
        personalBaseInfo.setUsername(username);
        return personalBaseInfo;

    }

    @Override
    public ApiResponse changeBaseInfo(ChangedPersonalInfo changedPersonalInfo) {
        String username = changedPersonalInfo.getUsername();
        if(userRepository.existsByUsername(username)){
            User user = userRepository.findByUsername(username).get();
            user.setGender(Gender.getGender(changedPersonalInfo.getGender()));
            user.setAge(changedPersonalInfo.getAge());
            user.setInstitution(changedPersonalInfo.getInstitution());
            user.setLivingPlace(changedPersonalInfo.getLivingPlace());
            userRepository.save(user);
            return ApiResponse.successResponse();
        }else{
            return new ApiResponse(false, "该任务不存在");
        }
    }
}
