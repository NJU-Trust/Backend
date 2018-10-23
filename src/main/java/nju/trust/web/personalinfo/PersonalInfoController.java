package nju.trust.web.personalinfo;

import nju.trust.payloads.personalinfomation.*;
import nju.trust.service.personalinfo.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

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
    public CampusPerformance getCampusPerformance(Principal principal) {
        return personalInformationService.getCampusPerformance(principal.getName());
    }

    // 信息表
    @GetMapping(value = "/information")
    public PersonalDetailInfomation getPersonalDetailInformation(Principal principal) {
        return personalInformationService.getPersonalDetailInformation(principal.getName());
    }

    // 账户总览 投资借款部分
    @GetMapping(value = "/investAndLoan")
    public InvestAndLoan getInvestAndLoanInfo(Principal principal) {
        return personalInformationService.getInvestAndLoanInfo(principal.getName());
    }

    // 校园关系图
    @GetMapping(value = "/relationship")
    public PersonalRelationship getPersonalRelationships(Principal principal) {
        return personalInformationService.getPersonalRelationships(principal.getName());
    }

    // 账户总额
    @GetMapping(value = "/totalAccount")
    public TotalAccountInfo getTotalAccountInfo(Principal principal) {
        return personalInformationService.getTotalAccountInfo(principal.getName());
    }

    // 待办事项
    @GetMapping(value = "/todo")
    public List<EventsInfo> getAllEventsInfo(Principal principal) {
        return personalInformationService.getAllEventsInfo(principal.getName());
    }
}
