package nju.trust.web.personalinfo;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.personalinfomation.ChangedPersonalInfo;
import nju.trust.payloads.personalinfomation.PersonalBaseInfo;
import nju.trust.service.personalinfo.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/16
 */
@RestController
@RequestMapping("/profile")
public class BaseInfoController {

    private BaseInfoService baseInfoService;

    @GetMapping(value = "/personalInformation")
    public PersonalBaseInfo getBaseInformation(Principal principal) {
        return baseInfoService.getBaseInfo(principal.getName());
    }

    @PostMapping(value = "/changeInformation")
    public ApiResponse changeBaseInformation(@RequestBody ChangedPersonalInfo changedPersonalInfo){
        return baseInfoService.changeBaseInfo(changedPersonalInfo);
    }


    @Autowired
    public BaseInfoController(BaseInfoService baseInfoService){
        this.baseInfoService = baseInfoService;
    }

}
