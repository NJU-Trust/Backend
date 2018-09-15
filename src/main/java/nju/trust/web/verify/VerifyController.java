package nju.trust.web.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import nju.trust.service.verify.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setVerifyService(VerifyService verifyService){
        this.verifyService = verifyService;
    }
}
