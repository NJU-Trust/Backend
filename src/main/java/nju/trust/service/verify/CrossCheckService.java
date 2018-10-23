package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.CrossCheckInfo;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
public interface CrossCheckService {

    ApiResponse setUpNetwork(String username, String studentId1,String studentId2,String studentId3);

    List<CrossCheckInfo> getQuestionnaireList(String username);

    ApiResponse submitQuestionnaire(long id, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9);

    double getCrossScore(String username);
}
