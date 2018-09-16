package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

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

    @Test
    public void getInvestAndLoanInfo() {
        System.out.println("test getInvestAndLoanInfo:");
        String username = "test";
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
        String username = "test";
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
    }

    @Test
    public void getCampusPerformance() {
        String username = "test";
        CampusPerformance result = test.getCampusPerformance(username);
        System.out.println("result:");
        System.out.println("学校 学历 社交情况 获奖情况 成绩");
        System.out.println("PersonalPerformance:"+result.getPersonalPerformance());
        System.out.println("AveragePerformance:"+result.getAveragePerformance());
        System.out.println("aboveAverage:"+result.getAboveAverage());
    }

    @Test
    public void getPersonalDetailInformation() {
        String username = "test";
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
        System.out.println("test getPersonalRelationships");
        String username = "test";
        System.out.println("username:"+username);
        List<PersonalRelationship> result = test.getPersonalRelationships(username);
        print(result);
    }
    private void print(List<PersonalRelationship> relationships) {
        System.out.println("relationships:");
        for(PersonalRelationship relationship : relationships) {
            print(relationship);
            System.out.println();
        }
    }
    private void print(PersonalRelationship relationship) {
        System.out.println("username:"+relationship.getUsername());
        System.out.println("othersName:"+relationship.getOthersName());
        System.out.println("creditScore:"+relationship.getCreditScore());
        System.out.println("financialScore:"+relationship.getFinancialScore());
        System.out.println("campusPerformanceScore:"+relationship.getCampusPerformanceScore());
        System.out.println("relationship:"+relationship.getRelationship());
        System.out.println("creditChange:"+relationship.getCreditChange());
    }
}