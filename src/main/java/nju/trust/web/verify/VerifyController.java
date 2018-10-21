package nju.trust.web.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import nju.trust.service.verify.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/15
 */
@RestController
@RequestMapping("/verify")
public class VerifyController {

    private VerifyService verifyService;

    @PostMapping(value = "/campus")
    public ApiResponse saveCampusVerifyInfo(SchoolVerifyInfo schoolVerifyInfo, String username){
        return verifyService.schoolVerify(schoolVerifyInfo,username);
    }

    @PostMapping(value = "/selfInfo")
    public ApiResponse saveSelfInfo(String username, int fail, List<String> report_cards, List<NameAndEvidence> school_rewards
            ,List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards, List<NameAndEvidence> country_rewards
            ,double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications){
        return verifyService.selfInfoVerify(username, fail,report_cards,school_rewards,city_rewards,province_rewards,country_rewards,volunteer,volunteer_img,self_qualifications);
    }

    @Autowired
    public void setVerifyService(VerifyService verifyService){
        this.verifyService = verifyService;
    }
}
