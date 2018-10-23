package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.CrossCheckInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrossCheckServiceTest {

    @Autowired
    private CrossCheckService crossCheckService;

    @Test
    public void setUpNetwork1() {
        ApiResponse apiResponse = crossCheckService.setUpNetwork("cross1","161250002","161250003","161250004");
        System.out.println(apiResponse.getSuccess());
        System.out.println(apiResponse.getMessage());
    }

    @Test
    public void setUpNetwork2() {
        ApiResponse apiResponse = crossCheckService.setUpNetwork("cross2","161250001","161250003","161250004");
        System.out.println(apiResponse.getSuccess());
        System.out.println(apiResponse.getMessage());
    }

    @Test
    public void getQuestionnaireList() {
        List<CrossCheckInfo> list = crossCheckService.getQuestionnaireList("cross2");
        for(int i=0;i<list.size();i++){
            System.out.println(i+":");
            System.out.println("id:"+list.get(i).getId());
            System.out.println("person stu_id:"+list.get(i).getStudentId());
        }
    }

    @Test
    public void submitQuestionnaire1() {
        crossCheckService.submitQuestionnaire(1,1,1,1,1,1,1,1,9,9);
        crossCheckService.submitQuestionnaire(2,4,4,4,3,4,4,1,5,9);
    }

    @Test
    public void submitQuestionnaire2() {
        crossCheckService.submitQuestionnaire(3,1,1,1,1,1,1,1,9,9);
        crossCheckService.submitQuestionnaire(4,4,4,4,3,4,4,1,5,9);
        crossCheckService.submitQuestionnaire(5,2,2,2,2,2,2,1,8,2);
    }

}