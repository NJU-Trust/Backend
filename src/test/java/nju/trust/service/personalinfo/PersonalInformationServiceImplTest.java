package nju.trust.service.personalinfo;

import nju.trust.dao.user.CreditRecordRepository;
import nju.trust.dao.user.UserCrossCheckRepository;
import nju.trust.dao.user.UserMonthlyStatisticsRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.CreditCrossCheck;
import nju.trust.entity.user.CreditRecord;
import nju.trust.entity.user.User;
import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.payloads.personalinfomation.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PersonalInformationServiceImplTest {
    @Autowired
    private PersonalInformationService test;
    @Autowired
    private UserCrossCheckRepository crossCheckRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMonthlyStatisticsRepository userMonthlyStatisticsRepository;
    @Autowired
    private CreditRecordRepository creditRecordRepository;

    @Test
    public void getInvestAndLoanInfo() {
        System.out.println("test getInvestAndLoanInfo:");
        String username = "cross11";
        System.out.println("username:"+username);
        InvestAndLoan result = test.getInvestAndLoanInfo(username);
        print(result);
    }
    private void print(InvestAndLoan record) {
        System.out.println("totalInvestment:"+record.getTotalInvestment());
        System.out.println("totalLoan:"+record.getTotalLoan());
        System.out.println("getMoney:"+record.getGetMoney());
        System.out.println("getMoneyProgress:"+record.getGetMoneyProgress());
        System.out.println("payMoney:"+record.getPayMoney());
        System.out.println("payMoneyProgress:"+record.getPayMoneyProgress());
        System.out.println("creditRatingScore:"+record.getCreditRatingScore());
        System.out.println("creditRating:"+record.getCreditRating());
    }

    @Test
    public void getTotalAccountInfo() {
        System.out.println("test getTotalAccountInfo");
        String username = "cross11";
        System.out.println("username:"+username);
        TotalAccountInfo result = test.getTotalAccountInfo(username);
        print(result);
    }
    private void print(TotalAccountInfo info) {
        System.out.println("totalAccount:" + info.getTotalAccount());
        System.out.println("balance:" + info.getBalance());
        System.out.println("frozenAmount:" + info.getFrozenAmount());
        System.out.println("pendingPI:" + info.getPendingPI());
        System.out.println("investmentInBidding:" + info.getInvestmentInBidding());
    }

    @Test
    public void getAllEventsInfo() {
        System.out.println("test getAllEventsInfo");
        String username = "cross11";
        System.out.println("username:"+username);
        List<EventsInfo> result = test.getAllEventsInfo(username);
        System.out.println("result:");
        for(EventsInfo info : result) {
            System.out.println("date:"+info.getDate());
            System.out.println("title:"+info.getTitle());
            System.out.println("description:"+info.getDescription());
            System.out.println();
        }
    }

    @Test
    public void getCampusPerformance() {
        String username = "cross11";
        CampusPerformance result = test.getCampusPerformance(username);
        System.out.println("result:");
        System.out.println("学校 学历 社交情况 获奖情况 成绩");
        System.out.println("PersonalPerformance:"+result.getPersonalPerformance());
        System.out.println("AveragePerformance:"+result.getAveragePerformance());
        System.out.println("aboveAverage:"+result.getAboveAverage());
    }

    @Test
    public void getPersonalDetailInformation() {
        String username = "cross11";
        PersonalDetailInfomation info = test.getPersonalDetailInformation(username);
        System.out.println("username:"+username);
        System.out.println("PersonalDetailInfomation:");
        System.out.println("schoolClass:"+info.getSchoolClass());
        System.out.println("majorCondition:"+info.getMajorCondition());
        System.out.println("educationBackground:"+info.getEducationBackground());
        System.out.println("financeSource:"+info.getFinanceSource());
        System.out.println("studyRank:"+info.getStudyRank());
        System.out.println("numNoPass:"+info.getNumNoPass());
        System.out.println("scholarship:"+info.getScholarship());
        System.out.println("researchCompetition:"+info.getResearchCompetition());
        System.out.println("awards:"+info.getAwards());
        System.out.println("punishment:"+info.getPunishment());
        System.out.println("payment:"+info.getSchoolClass());
        System.out.println("library:"+info.getLibrary());
        System.out.println("cheating:"+info.getCheating());
    }

    @Test
    public void getPersonalRelationships() {
        prepareData();

        System.out.println("test getPersonalRelationships");
        String username = "cross11";
        System.out.println("username:"+username);
        PersonalRelationship result = test.getPersonalRelationships(username);
        print(result);
    }
    private void prepareData() {
        System.out.println("prepare data");
        // 信用交叉检验
        CreditCrossCheck creditCrossCheck = new CreditCrossCheck();
        creditCrossCheck.setRelatedPerson(userRepository.findByUsername("cross1").get());
        creditCrossCheck.setUser(userRepository.findByUsername("test").get());
        creditCrossCheck.setDone(true);
        creditCrossCheck.setQ1(0);
        crossCheckRepository.save(creditCrossCheck);

        creditCrossCheck = new CreditCrossCheck();
        creditCrossCheck.setRelatedPerson(userRepository.findByUsername("cross2").get());
        creditCrossCheck.setUser(userRepository.findByUsername("test").get());
        creditCrossCheck.setDone(true);
        creditCrossCheck.setQ1(1);
        crossCheckRepository.save(creditCrossCheck);

        creditCrossCheck = new CreditCrossCheck();
        creditCrossCheck.setRelatedPerson(userRepository.findByUsername("cross3").get());
        creditCrossCheck.setUser(userRepository.findByUsername("test").get());
        creditCrossCheck.setDone(true);
        creditCrossCheck.setQ1(2);
        crossCheckRepository.save(creditCrossCheck);

        creditCrossCheck = new CreditCrossCheck();
        creditCrossCheck.setRelatedPerson(userRepository.findByUsername("cross4").get());
        creditCrossCheck.setUser(userRepository.findByUsername("test").get());
        creditCrossCheck.setDone(true);
        crossCheckRepository.save(creditCrossCheck);

        // 月数据统计
        LocalDate date = LocalDate.now().minusMonths(1);

        UserMonthStatistics userMonthStatistics = new UserMonthStatistics();
        userMonthStatistics.setUser(userRepository.findByUsername("test").get());
        userMonthStatistics.setIncome(100.0);
        userMonthStatistics.setRigidRatio(100.0);
        userMonthStatistics.setDebtToAssetRatio(100.0);
        userMonthStatistics.setConsumptionRatio(100.0);
        userMonthStatistics.setDate(date);
        userMonthlyStatisticsRepository.save(userMonthStatistics);

        // 信用
        User user = userRepository.findByUsername("cross2").get();
        user.setCreditScore(15.0);
        userRepository.save(user);

        CreditRecord record = new CreditRecord();
        record.setUser(user);
        record.setCreditScore(20.0);
        record.setDate(LocalDate.of(2018, 9, 1));
        creditRecordRepository.save(record);

        record = new CreditRecord();
        record.setUser(user);
        record.setCreditScore(90.0);
        record.setDate(LocalDate.of(2018, 8, 1));
        creditRecordRepository.save(record);

        user = userRepository.findByUsername("cross3").get();
        user.setCreditScore(100.0);
        userRepository.save(user);

        record = new CreditRecord();
        record.setUser(user);
        record.setCreditScore(90.0);
        record.setDate(LocalDate.of(2018, 9, 1));
        creditRecordRepository.save(record);

        record = new CreditRecord();
        record.setUser(user);
        record.setCreditScore(20.0);
        record.setDate(LocalDate.of(2018, 8, 1));
        creditRecordRepository.save(record);
    }
    private void print(PersonalRelationship relationship) {
        System.out.println("peoples:");
        List<People> peoples = relationship.getPeople();
        for(People people : peoples) {
            print(people);
            System.out.println();
        }
        System.out.println();
        System.out.println("relations:");
        List<Relation> relations = relationship.getRelations();
        for(Relation relation : relations) {
            print(relation);
            System.out.println();
        }
    }
    private void print(Relation relation) {
        System.out.println("source:"+relation.getSource());
        System.out.println("target:"+relation.getTarget());
        System.out.println("name:"+relation.getName());
        System.out.println("creditChange:"+relation.getCreditChange());
    }
    private void print(People people) {
        System.out.println("name:"+people.getName());
        System.out.println("creditPts:"+people.getCreditPts());
        System.out.println("financialPts:"+people.getFinancialPts());
        System.out.println("schoolPts:"+people.getSchoolPts());
    }

    @Test
    public void getDataAnalysis() {
        System.out.println();
        System.out.println("test getDataAnalysis:");
        String username = "cross11";
        String startMonth = "2018-9";
        String endMonth = "2018-10";

        DataAnalysis result = test.getDataAnalysis(username, startMonth, endMonth);
        print(result);
    }
    private void print(DataAnalysis result) {
        System.out.println("DataAnalysis:");
        System.out.println("每月信息 monthAnalysisList:");
        List<MonthAnalysis> monthAnalysisList = result.getMonthAnalysisList();
        for(MonthAnalysis monthAnalysis : monthAnalysisList) {
            print(monthAnalysis);
        }
        System.out.println();
        System.out.println("总收入 incomeSum="+result.getIncomeSum());
        System.out.println();
        System.out.println("总支出 expenseSum="+result.getExpenseSum());
        System.out.println();
        System.out.println("总刚性支出 expense_rigSum="+result.getExpense_rigSum());
        System.out.println();
        System.out.println("总可调支出 expense_discSum="+result.getExpense_discSum());
        System.out.println();
        System.out.println("总投资额结余 surplusSum="+result.getSurplusSum());
        System.out.println();
        System.out.println("总负债 lbltSum="+result.getLbltSum());
        System.out.println();
        System.out.println("总净资产 assetSum="+result.getAssetSum());
    }
    private void print(MonthAnalysis monthAnalysis) {
        System.out.println("month:"+monthAnalysis.getMonth()+"  "
                            + "income:"+monthAnalysis.getIncome()+"  "
                            + "expense:"+monthAnalysis.getExpense()+"  "
                            + "expense_rig:"+monthAnalysis.getExpense_rig()+"  "
                            + "expense_disc:"+monthAnalysis.getExpense_disc()+"  "
                            + "surplus:"+monthAnalysis.getSurplus()+"  "
                            + "lblt:"+monthAnalysis.getLblt()+"  "
                            + "asset:"+monthAnalysis.getAsset()+"  ");
    }

    @Test
    public void getTrendAnalysis() {
        System.out.println();
        System.out.println("test getTrendAnalysis:");
        String username = "cross11";
        String startMonth = "2018-1";
        String endMonth = "2018-10";

        List<TrendAnalysis> result = test.getTrendAnalysis(username, startMonth, endMonth);

        for(TrendAnalysis trendAnalysis : result) {
            print(trendAnalysis);
        }
    }
    private void print(TrendAnalysis trendAnalysis) {
        System.out.println("month:"+trendAnalysis.getMonth()+"  "
                +"engel:"+trendAnalysis.getEngel()+"  "
                +"rig_ratio:"+trendAnalysis.getRig_ratio()+"  "
                +"d2a_ratio:"+trendAnalysis.getD2a_ratio()+"  "
                +"dp_ability:"+trendAnalysis.getDp_ability()+"  "
                +"leverage:"+trendAnalysis.getLeverage()+"  "
                +"consump_ratio:"+trendAnalysis.getConsump_ratio()+"  "
                +"saving_ratio:"+trendAnalysis.getSaving_ratio()+"  ");
        System.out.println();
    }

    @Test
    public void getProportionAnalysis() {
        System.out.println();
        System.out.println("test getProportionAnalysis:");

        String username = "cross11";
        String month = "2018-1";

        ProportionAnalysis proportionAnalysis = test.getProportionAnalysis(username, month);

        System.out.println("result:");
        print(proportionAnalysis);
    }
    private void print(ProportionAnalysis proportionAnalysis) {
        System.out.println("月支出:"+proportionAnalysis.getOutcome());
        System.out.println("月可调支出:"+proportionAnalysis.getAdjust());
        System.out.println("月饮食支出:"+proportionAnalysis.getFood());
        System.out.println("支出模块:");
        print(proportionAnalysis.getData1());
        System.out.println("可调支出:");
        print(proportionAnalysis.getData2());
        System.out.println("饮食支出:");
        print(proportionAnalysis.getData3());
    }
    private void print(ProportionOutcome proportionOutcome) {
        System.out.println("日常:"+proportionOutcome.getDaily());
        System.out.println("学习:"+proportionOutcome.getLearning());
        System.out.println("饮食:"+proportionOutcome.getFood());
        System.out.println("出行:"+proportionOutcome.getTravel());
        System.out.println("娱乐:"+proportionOutcome.getFun());
    }
    private void print(ProportionAdjust proportionAdjust) {
        System.out.println("衣物:"+proportionAdjust.getDress());
        System.out.println("饮食:"+proportionAdjust.getFood());
        System.out.println("住宿:"+proportionAdjust.getHotel());
        System.out.println("娱乐:"+proportionAdjust.getFun());
    }
    private void print(ProportionFood proportionFood) {
        System.out.println("食堂:"+proportionFood.getSchoolCanteen());
        System.out.println("外卖:"+proportionFood.getTakeOut());
        System.out.println("外出:"+proportionFood.getEatingOut());
        System.out.println("零食:"+proportionFood.getSnackAndFruit());
    }
}