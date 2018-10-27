package nju.trust.web.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import nju.trust.service.verify.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @GetMapping(value = "/campus")
    public ApiResponse saveCampusVerifyInfo(SchoolVerifyInfo schoolVerifyInfo, Principal principal){
        return verifyService.schoolVerify(schoolVerifyInfo,principal.getName());
    }

    @GetMapping(value = "/selfInfo")
    public ApiResponse saveSelfInfo(Principal principal, int fail, List<String> report_cards, List<NameAndEvidence> school_rewards
            ,List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards, List<NameAndEvidence> country_rewards
            ,double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications){
        return verifyService.selfInfoVerify(principal.getName(), fail,report_cards,school_rewards,city_rewards,province_rewards,country_rewards,volunteer,volunteer_img,self_qualifications);
    }

    // TODO 校友信息验证
    @GetMapping(value = "/alumnaVerify")
    public ApiResponse alumnaVerify(Principal principal, String gender, String birthday, String idCardNumber, String education, String evidence) {
        return verifyService.alumnaVerify(principal.getName(), gender, birthday, idCardNumber, education, evidence);
    }

    // 得到用户的角色
    @GetMapping(value = "/getRoles")
    public List<String> getRoles(Principal principal) {
        return verifyService.getRoles(principal.getName());
    }

    @Autowired
    public void setVerifyService(VerifyService verifyService){
        this.verifyService = verifyService;
    }
}
