package nju.trust.web.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.CrossCheckInfo;
import nju.trust.service.verify.CrossCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
@RestController
@RequestMapping("/crossCheck")
public class CrossCheckController {

    private CrossCheckService crossCheckService;

    @GetMapping(value = "setUpNetwork")
    public ApiResponse setUpNetwork(String username, String studentId1,String studentId2,String studentId3){
        return crossCheckService.setUpNetwork(username,studentId1,studentId2,studentId3);
    }

    @GetMapping(value = "questionnaireList")
    public List<CrossCheckInfo> getQuestionnaireList(String username){
        return crossCheckService.getQuestionnaireList(username);
    }

    @PostMapping(value = "submit")
    public ApiResponse submitQuestionnaire(long id, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9){
        return crossCheckService.submitQuestionnaire(id, q1, q2, q3, q4, q5, q6, q7, q8, q9);
    }

    @GetMapping(value = "/crossScore")
    public double getCrossScore(String username){
        return 0.0;
    }

    @Autowired
    public CrossCheckController(CrossCheckService crossCheckService) {
        this.crossCheckService = crossCheckService;
    }
}
