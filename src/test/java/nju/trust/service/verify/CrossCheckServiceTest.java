package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void setUpNetwork() {
        ApiResponse apiResponse = crossCheckService.setUpNetwork("cross1","161250002","161250003","161250004");
        System.out.println(apiResponse.getSuccess());
        System.out.println(apiResponse.getMessage());
    }

    @Test
    public void getQuestionnaireList() {
    }

    @Test
    public void submitQuestionnaire() {
    }
}