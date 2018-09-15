package nju.trust.service.personalinfo;

import nju.trust.dao.admin.BaseTargetRepository;
import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.record.UserEvidence.*;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import nju.trust.entity.user.User;
import nju.trust.payloads.personalinfomation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
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
    private BaseTargetRepository baseTargetRepository;
    private UserEvidenceRepository userEvidenceRepository;
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    private static final String noComplete = "未填写";
    @Autowired
    public PersonalInformationServiceImpl(UserRepository userRepository, InvestmentRecordRepository investmentRecordRepository, UnstructuredDataRepository unstructuredDataRepository, BaseTargetRepository baseTargetRepository, UserEvidenceRepository userEvidenceRepository, UserInfoCheckRecordRepository userInfoCheckRecordRepository) {
        this.userRepository = userRepository;
        this.investmentRecordRepository = investmentRecordRepository;
        this.unstructuredDataRepository = unstructuredDataRepository;
        this.baseTargetRepository = baseTargetRepository;
        this.userEvidenceRepository = userEvidenceRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
    }

    // TODO code
    @Override
    public InvestAndLoan getInvestAndLoanInfo(String username) {
        User user = userRepository.findByUsername(username).get();
        InvestAndLoan info = new InvestAndLoan();
        info.setTotalInvestment(calTotalInvestment(username));
        info.setTotalLoan(calTotalLoan(username));
        info.setGetMoney(calSetGetMoney(username));
        info.setGetMoneyProgress(calSetGetMoneyRrogress(username));
        info.setPayMoney(calSetPayMoney(username));
        info.setPayMoneyProgress(calSetPayMoneyRrogress(username));
        info.setCreditRating(user.getCreditRating().toString());
        info.setCreditRatingScore(user.getCreditScore());
        return info;
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
    // TODO test 借款总额
    private double calTotalLoan(String username) {
        List<BaseTarget> targets = baseTargetRepository.findDistinctByUserUsername(username);
        double sum = 0;
        if(targets == null) {
            return sum;
        }
        for(BaseTarget target : targets) {
            sum = sum + target.getMoney();
        }
        return sum;
    }
    // TODO code 待收回本息
    private double calSetGetMoney(String username) {
        return 0;
    }
    // TODO code
    private double calSetGetMoneyRrogress(String username) {
        return 0;
    }
    // TODO code
    private double calSetPayMoney(String username) {
        return 0;
    }
    // TODO code
    private double calSetPayMoneyRrogress(String username){
        return 0;
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

        for(UnstructuredDataType type : types) {
            double personal = getPersonalPerformance(username, type);
            personalPerformance.add(personal);
            double average = getAveragePerformance(type);
            averagePerformance.add(average);
        }

        CampusPerformance performance = new CampusPerformance();
        performance.setPersonalPerformance(personalPerformance);
        performance.setAveragePerformance(averagePerformance);
        performance.setAboveAverage(calArea(personalPerformance) > calArea(averagePerformance));

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
    // 计算面积
    private double calArea(List<Double> record) {
        double area = 0;
        for(int i = 0 ; i < record.size() ; i++) {
            for (int j = i+1 ; j < record.size() ; j++) {
                area = area + record.get(i)*record.get(j);
            }
        }
        return area;
    }

    /**
     * 信息表
     * @param username 用户名
     * @return
     */
    @Override
    public PersonalDetailInfomation getPersonalDetailInformation(String username) {
        String schoolType = getSchoolType(username);
        String majorType = getMajorType(username);
        String educationType = getEducationType(username);
        String financeSource = getFinanceSource(username);
        String studyRank = getStudyRank(username);
        int noPass = getNoPass(username);
        List<String> scholarship = getScholarship(username);
        List<String> researchCompetition = getResearchCompetition(username);
        List<String> awards = getAwards(username);
        List<String> punishment = getPunishment(username);
        int payment = getPayment(username);
        int library = getLibrary(username);
        int cheating = getCheating(username);

        PersonalDetailInfomation info = new PersonalDetailInfomation();
        info.setSchoolClass(schoolType);
        info.setMajorCondition(majorType);
        info.setEducationBackground(educationType);
        info.setFinanceSource(financeSource);
        info.setStudyRank(studyRank);
        info.setNumNoPass(noPass);
        info.setScholarship(scholarship);
        info.setResearchCompetition(researchCompetition);
        info.setAwards(awards);
        info.setPunishment(punishment);
        info.setPayment(payment);
        info.setLibrary(library);
        info.setCheating(cheating);

        return info;
    }
    // 学校分类
    private String getSchoolType(String username) {
        List<SchoolEvidence> schoolEvidence = userEvidenceRepository.findSchoolEvidenceByUser(username);
        if(schoolEvidence == null || schoolEvidence.size() == 0) {
            return noComplete;
        }
        return schoolEvidence.get(schoolEvidence.size()-1).getSchoolType().getStr();
    }
    // 专业情况
    private String getMajorType(String username) {
        List<MajorEvidence> majorEvidences = userEvidenceRepository.findMajorEvidenceByUser(username);
        if(majorEvidences == null || majorEvidences.size() == 0) {
            return noComplete;
        }
        return majorEvidences.get(majorEvidences.size()-1).getMajorType().getStr();
    }
    // 受教育情况
    private String getEducationType(String username) {
        List<EducationEvidence> educationEvidences = userEvidenceRepository.findEducationEvidenceByUser(username);
        if(educationEvidences == null || educationEvidences.size() == 0) {
            return noComplete;
        }
        return educationEvidences.get(educationEvidences.size()-1).getEducationType().getStr();
    }
    // 经济来源
    private String getFinanceSource(String username) {
        UnstructuredData data = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, UnstructuredDataType.ECONOMIC);
        if(data == null || data.getDescription() == null || data.getDescription().length() == 0) {
            return noComplete;
        }
        return data.getDescription();
    }
    // 学习成绩
    private String getStudyRank(String username) {
        List<StudyEvidence> studyEvidences = userEvidenceRepository.findStudyEvidenceByUser(username);
        if(studyEvidences == null || studyEvidences.size() == 0) {
            return noComplete;
        }
        double rank = studyEvidences.get(studyEvidences.size()-1).getRanking();
        rank = (int)(rank*100)/100.0;
        return rank+"%";
    }
    // 挂科数
    private int getNoPass(String username) {
        List<FailEvidence> failEvidences = userEvidenceRepository.findFailEvidenceByUser(username);
        if(failEvidences == null || failEvidences.size() == 0) {
            return 0;
        }
        return failEvidences.get(0).getNum();
    }
    // 奖学金
    private List<String> getScholarship(String username) {
        return getStrRecord(username, CheckItem.SCHOLARSHIP);
    }
    // 科研竞赛获奖情况
    private List<String> getResearchCompetition(String username) {
        return getStrRecord(username, CheckItem.SCHOLARSHIP);
    }
    // 奖励情况
    private List<String> getAwards(String username) {
        return getStrRecord(username, CheckItem.REWARD);
    }
    // 惩罚情况
    private List<String> getPunishment(String username) {
        return getStrRecord(username, CheckItem.PUNISHMENT);
    }
    // 学费及住宿费缴纳状况
    private int getPayment(String username) {
        return getStrRecord(username, CheckItem.PAYMENT).size();
    }
    // 图书馆借阅还书情况
    private int getLibrary(String username) {
        return getStrRecord(username, CheckItem.RETURNBOOKS).size();
    }
    // 考试作弊的信息
    private int getCheating(String username) {
        return getStrRecord(username, CheckItem.TESTCHEAT).size();
    }
    private List<String> getStrRecord(String username, CheckItem item) {
        List<UserInfoCheckRecord> records = userInfoCheckRecordRepository.findDistinctByUserUsernameAndAndCheckItem(username, item);
        List<String> scholarships = new ArrayList<>();
        if(records == null) {
            return scholarships;
        }
        for(UserInfoCheckRecord record : records) {
            if(record.getCheckState().equals(CheckState.PASS)) {
                scholarships.add(record.getDescription());
            }
        }
        return scholarships;}

    /**
     * TODO code
     * @param username 用户名
     * @return
     */
    @Override
    public List<PersonalRelationship> getPersonalRelationships(String username) {

        return null;
    }
}
