package nju.trust.web.personalinfo;

import nju.trust.entity.UserType;
import nju.trust.payloads.admin.UserListRequest;
import nju.trust.payloads.personalinfomation.CampusPerformance;
import nju.trust.payloads.personalinfomation.PersonalDetailInfomation;
import nju.trust.service.personalinfo.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/15
 */
@RestController
@RequestMapping("/profile")
public class PersonalInfoController {
    private PersonalInformationService personalInformationService;
    @Autowired
    public PersonalInfoController(PersonalInformationService personalInformationService) {
        this.personalInformationService = personalInformationService;
    }

    // 用户管理
    @GetMapping(value = "/campusPerformence")
    public CampusPerformance getCampusPerformance(String username) {
        return personalInformationService.getCampusPerformance(username);
    }

    // 信息表
    @GetMapping(value = "/information")
    public PersonalDetailInfomation getPersonalDetailInformation(String username) {
        return personalInformationService.getPersonalDetailInformation(username);
    }
}
