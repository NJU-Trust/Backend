package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface PersonalInformationService {
    /**
     * 投资借款部分
     * @param username 用户名
     * @return
     */
    InvestAndLoan getInvestAndLoanInfo(String username);

    /**
     * 账户总额
     * @param username 用户名
     * @return
     */
    TotalAccountInfo getTotalAccountInfo(String username);

    /**
     * 待办列表
     * @param username 用户名
     * @return
     */
    List<EventsInfo> getAllEventsInfo(String username);

    /**
     * 校园表现
     * @param username 用户名
     * @return
     */
    CampusPerformance getCampusPerformance(String username);

    /**
     * 信息表
     * @param username 用户名
     * @return
     */
    PersonalDetailInfomation getPersonalDetailInformation(String username);

    /**
     * 校园关系图
     * @param username 用户名
     * TODO
     */
    PersonalRelationship getPersonalRelationships(String username);

    /**
     * 数值分析
     * @param username 用户名
     * @param startMonth 开始月份
     * @param endMonth 结束月份
     * 月份格式：2018-1
     * 时间为闭区间
     */
    DataAnalysis getDataAnalysis(String username, String startMonth, String endMonth);

    /**
     * 趋势分析
     * @param username 用户名
     * @param startMonth 开始月份
     * @param endMonth 结束月份
     * 月份格式：2018-1
     * 时间为闭区间
     */
    List<TrendAnalysis> getTrendAnalysis(String username, String startMonth, String endMonth);

    /**
     * 比例分析
     * @param username 用户名
     * @param month 月份
     * 月份格式：2018-1
     */
    ProportionAnalysis getProportionAnalysis(String username, String month);
}
