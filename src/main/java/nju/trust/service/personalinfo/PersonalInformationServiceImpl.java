package nju.trust.service.personalinfo;

import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import nju.trust.entity.user.User;
import nju.trust.payloads.personalinfomation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {
    private UserRepository userRepository;
    private InvestmentRecordRepository investmentRecordRepository;
    private UnstructuredDataRepository unstructuredDataRepository;
    @Autowired
    public PersonalInformationServiceImpl(UserRepository userRepository, InvestmentRecordRepository investmentRecordRepository, UnstructuredDataRepository unstructuredDataRepository) {
        this.userRepository = userRepository;
        this.investmentRecordRepository = investmentRecordRepository;
        this.unstructuredDataRepository = unstructuredDataRepository;
    }

    // TODO code
    @Override
    public InvestAndLoan getInvestAndLoanInfo(String username) {
        User user = userRepository.findByUsername(username).get();
        InvestAndLoan info = new InvestAndLoan();
        info.setTotalInvestment(calTotalInvestment(username));
        info.setTotalLoan(calTotalLoan(username));
        //info.setPendingPrincipal(calPendingPrincipal(username));
        //info.setPendingInterest(calPendingInterest(username));
        info.setCreditRating(user.getCreditRating().toString());
        info.setCreditRatingScore(user.getCreditScore());
        return info;
    }

    /**
     * TODO code
     * @param username 用户名
     * @return
     */
    @Override
    public TotalAccountInfo getTotalAccountInfo(String username) {
        return null;
    }

    /**
     * TODO code
     * @param username 用户名
     * @return
     */
    @Override
    public List<EventsInfo> getAllEventsInfo(String username) {
        return null;
    }

    /**
     * 校园表现
     * @param username 用户名
     * @return
     */
    @Override
    public CampusPerformance getCampusPerformance(String username) {
        // 学校、学历、社交情况、获奖情况、成绩
        List<UnstructuredDataType> types = Arrays.asList(UnstructuredDataType.SCHOOL, UnstructuredDataType.EDUCATION, UnstructuredDataType.SOCIALITY, UnstructuredDataType.AWARD, UnstructuredDataType.GRADE);
        List<Double> personalPerformance = new ArrayList<>();
        List<Double> averagePerformance = new ArrayList<>();
        double person = 1;
        double platform = 1;

        for(UnstructuredDataType type : types) {
            double personal = getPersonalPerformance(username, type);
            personalPerformance.add(personal);

            person = person * personal;
            double average = getAveragePerformance(type);
            averagePerformance.add(average);
            platform = platform * average;
        }

        CampusPerformance performance = new CampusPerformance();
        performance.setPersonalPerformance(personalPerformance);
        performance.setAveragePerformance(averagePerformance);
        performance.setAboveAverage(person > platform);

        return performance;
    }
    // 得到个人表现
    private double getPersonalPerformance(String username, UnstructuredDataType type) {
        UnstructuredData unstructuredData = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, type);
        if(unstructuredData == null) {
            return type.getInitialScore();
        }
        return unstructuredData.getScore();
    }
    // 得到平台表现
    private double getAveragePerformance(UnstructuredDataType type) {
        int userNum = ((List)userRepository.findAll()).size()-1;
        List<UnstructuredData> dataList = unstructuredDataRepository.findDistinctByDataType(type);
        double sum = 0;
        for(UnstructuredData data : dataList) {
            sum = sum + data.getScore();
        }
        return sum/userNum;
    }

    /**
     * TODO code
     * @param username 用户名
     * @return
     */
    @Override
    public PersonalDetailInfomation getPersonalDetailInformation(String username) {
        return null;
    }

    /**
     * TODO code
     * @param username 用户名
     * @return
     */
    @Override
    public List<PersonalRelationship> getPersonalRelationships(String username) {

        return null;
    }

    // TODO test 计算投资总额
    private double calTotalInvestment(String username) {
        List<InvestmentRecord> records = investmentRecordRepository.findAllByUserUsername(username);
        double sum = 0;
        for(InvestmentRecord record : records) {
            sum = sum + record.getInvestedMoney();
        }
        return sum;
    }
    // TODO code
    private double calTotalLoan(String username) {
        return 0;
    }
    // TODO code
    private double calPendingPrincipal(String username) {return 0;}
    // TODO code
    private double calPendingInterest(String username){return 0;}
}
