package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.CrossCheckInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
@Service
public class CrossCheckServiceImpl implements CrossCheckService {
    @Override
    public ApiResponse setUpNetwork(String username, String studentId1, String studentId2, String studentId3) {
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
