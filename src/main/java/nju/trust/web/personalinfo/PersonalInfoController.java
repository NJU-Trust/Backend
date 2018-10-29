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
    public CampusPerformance getCampusPerformance(String username) {
        return personalInformationService.getCampusPerformance(username);
    }

    // 信息表
    @GetMapping(value = "/information")
    public PersonalDetailInfomation getPersonalDetailInformation(String username) {
        return personalInformationService.getPersonalDetailInformation(username);
    }

    // 账户总览 投资借款部分
    @GetMapping(value = "/investAndLoan")
    public InvestAndLoan getInvestAndLoanInfo(String username) {
        return personalInformationService.getInvestAndLoanInfo(username);
    }

    // 校园关系图
    @GetMapping(value = "/relationship")
    public PersonalRelationship getPersonalRelationships(String username) {
        return personalInformationService.getPersonalRelationships(username);
    }

    // 账户总额
    @GetMapping(value = "/totalAccount")
    public TotalAccountInfo getTotalAccountInfo(String username) {
        return personalInformationService.getTotalAccountInfo(username);
    }

    // 待办事项
    @GetMapping(value = "/todo")
    public List<EventsInfo> getAllEventsInfo(String username) {
        return personalInformationService.getAllEventsInfo(username);
    }

    // 数值分析
    @GetMapping(value = "/dataAnalysis")
    public DataAnalysis getDataAnalysis(String username, String startMonth, String endMonth) {
        return personalInformationService.getDataAnalysis(username, startMonth, endMonth);
    }

    // 趋势分析
    @GetMapping(value = "/trendAnalysis")
    public List<TrendAnalysis> getTrendAnalysis(String username, String startMonth, String endMonth) {
        return personalInformationService.getTrendAnalysis(username, startMonth, endMonth);
    }

    // 比例分析
    @GetMapping(value = "/proportionAnalysis")
    public ProportionAnalysis getProportionAnalysis(String username, String month) {
        return personalInformationService.getProportionAnalysis(username, month);
    }
}
