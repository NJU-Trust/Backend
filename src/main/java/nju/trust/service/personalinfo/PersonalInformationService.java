package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.*;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface PersonalInformationService {
    /**
     *
     * @param username 用户名
     * @return
     */
    InvestAndLoan getInvestAndLoanInfo(String username);
    /**
     *
     * @param username 用户名
     * @return
     */
    TotalAccountInfo getTotalAccountInfo(String username);
    /**
     *
     * @param username 用户名
     * @return
     */
    List<EventsInfo> getAllEventsInfo(String username);
    /**
     *
     * @param username 用户名
     * @return
     */
    CampusPerformance getCampusPerformance(String username);
    /**
     *
     * @param username 用户名
     * @return
     */
    PersonalDetailInfomation getPersonalDetailInformation(String username);
    /**
     *
     * @param username 用户名
     * @return
     */
    List<PersonalRelationship> getPersonalRelationships(String username);
}
