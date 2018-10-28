package nju.trust.web.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import nju.trust.payloads.verifyInfo.SelfInfo;
import nju.trust.service.verify.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/selfInfo")
    public ApiResponse saveSelfInfo(Principal principal, @RequestBody SelfInfo selfInfo){
        return verifyService.selfInfoVerify(principal.getName(), selfInfo.getFail(), selfInfo.getReport_cards(), selfInfo.getSchool_rewards(), selfInfo.getCity_rewards(), selfInfo.getProvince_rewards(), selfInfo.getCountry_rewards(), selfInfo.getVolunteer(), selfInfo.getVolunteer_img(), selfInfo.getSelf_qualifications());
    }

    // 校友信息验证
    @GetMapping(value = "/alumnaVerify")
    public ApiResponse alumnaVerify(Principal principal, String gender, String birthday, String idCardNumber, String education, String evidence, String institution, String livingPlace) {
        return verifyService.alumnaVerify(principal.getName(), gender, birthday, idCardNumber, education, evidence, institution, livingPlace);
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
