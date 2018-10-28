package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/10/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class VerifyServiceImplTest {

    @Autowired
    private VerifyService test;

    @Test
    public void schoolVerify() {
        SchoolVerifyInfo info = new SchoolVerifyInfo();
        info.setRealName("唐佳未");
        info.setGender("女");
        info.setBirthday("2018-01-01T");
        info.setIdCardNumber("idCardNumber   ");
        info.setUniversity("NJU");
        info.setInstitution("SE");
        info.setMajor("SE");
        info.setAlipay("alipay");
        info.setStuCardImage("stuCardImage");
        info.setSchoolCardImage("schoolCardImage");
        String username = "test";

        ApiResponse response = test.schoolVerify(info, username);

        System.out.println("response:"+response.getSuccess()+"  "+response.getMessage());
    }

    @Test
    public void selfInfoVerify() {
        List<String> report_cards = new ArrayList<>();
        report_cards.add("report_cards1");
        report_cards.add("report_cards2");

        List<NameAndEvidence> school_rewards = new ArrayList<>();
        school_rewards.add(new NameAndEvidence("校级奖学金", "school scholarship evidence"));
        school_rewards.add(new NameAndEvidence("花旗杯", "school match evidence"));

        List<NameAndEvidence> city_rewards = new ArrayList<>();
        city_rewards.add(new NameAndEvidence("市级奖学金", "city scholarship evidence"));

        List<NameAndEvidence> province_rewards = new ArrayList<>();
        province_rewards.add(new NameAndEvidence("省级三好学生", "province 3good stu evidence"));

        List<NameAndEvidence> country_rewards = new ArrayList<>();
        country_rewards.add(new NameAndEvidence("国家级奖学金", "national scholarship evidence"));

        String volunteer_img = "volunteer evidence";

        List<NameAndEvidence> self_qualifications = new ArrayList<>();
        self_qualifications.add(new NameAndEvidence("计算机二级证书", "qualification evidence"));

        ApiResponse response = test.selfInfoVerify("test1", 1, report_cards, school_rewards, city_rewards, province_rewards, country_rewards, 1, volunteer_img, self_qualifications);
        System.out.println();
        System.out.println("test selfInfoVerify:");
        System.out.println("result: success = "+response.getSuccess()+"  message = "+response.getMessage());
    }

    @Test
    public void getRoles() {
        String username = "test";
        List<String> result = test.getRoles(username);
        System.out.println();
        System.out.println("result:"+ Arrays.toString(result.toArray()));
    }

    @Test
    public void alumnaVerify() {
        String username = "test";
        String gender = "女";
        String birthday = "1998-02-16";
        String idCardNumber = "410425199802160521";
        String education = "本科";
        String evidence = "education evidence";
        ApiResponse result = test.alumnaVerify(username, gender, birthday, idCardNumber, education, evidence, "institution", "living place");
        System.out.println();
        System.out.println("result:"+ result.getSuccess()+"  "+result.getMessage());
    }
}