package nju.trust.web.personalinfo;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.personalinfomation.ChangedPersonalInfo;
import nju.trust.payloads.personalinfomation.PersonalBaseInfo;
import nju.trust.service.personalinfo.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PersonalBaseInfo getBaseInformation(String username) {
        return baseInfoService.getBaseInfo(username);
    }

    @PostMapping(value = "changeInformation")
    public ApiResponse changeBaseInformation(ChangedPersonalInfo changedPersonalInfo){
        return baseInfoService.changeBaseInfo(changedPersonalInfo);
    }

    @Autowired
    public BaseInfoController(BaseInfoService baseInfoService){
        this.baseInfoService = baseInfoService;
    }

}
