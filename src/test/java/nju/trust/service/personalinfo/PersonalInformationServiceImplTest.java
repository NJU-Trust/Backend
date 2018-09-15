package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.CampusPerformance;
import nju.trust.payloads.personalinfomation.PersonalDetailInfomation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

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
    }

    @Test
    public void getTotalAccountInfo() {
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
    }
}