package nju.trust.service.verify;

import nju.trust.dao.user.UserCrossCheckRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.CrossCheckInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
@Service
public class CrossCheckServiceImpl implements CrossCheckService {

    private UserRepository userRepository;
    private UserCrossCheckRepository userCrossCheckRepository;

    public CrossCheckServiceImpl(UserRepository userRepository, UserCrossCheckRepository userCrossCheckRepository) {
        this.userRepository = userRepository;
        this.userCrossCheckRepository = userCrossCheckRepository;
    }

    @Override
    public ApiResponse setUpNetwork(String username, String studentId1, String studentId2, String studentId3) {
        if(!userRepository.existsByUsername(username)){
            return new ApiResponse(false,"username not exist!");
        }
        if(userRepository.existsByStudentId(studentId1)&&userRepository.existsByStudentId(studentId2)&&userRepository.existsByStudentId(studentId3)){
            //1.find users having the same institution
            User user = userRepository.findByUsername(username).get();
            Optional<User> users = userRepository.findByInstitution(user.getInstitution());
            //2.find random 7 person and set up network
            
        }else {
            return new ApiResponse(false,"studentId not exist!");
        }
        return null;
    }

    @Override
    public List<CrossCheckInfo> getQuestionnaireList(String username) {
        return null;
    }

    @Override
    public ApiResponse submitQuestionnaire(long id, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9) {
        return null;
    }
}
