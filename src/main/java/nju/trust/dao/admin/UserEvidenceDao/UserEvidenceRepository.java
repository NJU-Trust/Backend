package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.BaseUserEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用于统一用户信息凭证的存取
 * @Date: 2018/9/8
 */
@Service
public class UserEvidenceRepository {
    private BaseUserEvidenceRepository baseUserEvidenceRepository;
    private DiscreditEvidenceRepository discreditEvidenceRepository;
    private EducationEvidenceRepository educationEvidenceRepository;
    private FailEvidenceRepository failEvidenceRepository;
    private MajorEvidenceRepository majorEvidenceRepository;
    private MatchEvidenceRepository matchEvidenceRepository;
    private PaymentEvidenceRepository paymentEvidenceRepository;
    private PunishmentEvidenceRepository punishmentEvidenceRepository;
    private RepaymentEvidenceRepository repaymentEvidenceRepository;
    private ReturnBooksEvidenceRepository returnBooksEvidenceRepository;
    private RewardEvidenceRepository rewardEvidenceRepository;
    private ScholarshipEvidenceRepository scholarshipEvidenceRepository;
    private SchoolEvidenceRepository schoolEvidenceRepository;
    private StudentWorkEvidenceRepository studentWorkEvidenceRepository;
    private StudyEvidenceRepository studyEvidenceRepository;
    private TestCheatEvidenceRepository testCheatEvidenceRepository;
    private VolunteerEvidenceRepository volunteerEvidenceRepository;
    @Autowired
    public UserEvidenceRepository(BaseUserEvidenceRepository baseUserEvidenceRepository, DiscreditEvidenceRepository discreditEvidenceRepository, EducationEvidenceRepository educationEvidenceRepository, FailEvidenceRepository failEvidenceRepository, MajorEvidenceRepository majorEvidenceRepository, MatchEvidenceRepository matchEvidenceRepository, PaymentEvidenceRepository paymentEvidenceRepository, PunishmentEvidenceRepository punishmentEvidenceRepository, RepaymentEvidenceRepository repaymentEvidenceRepository, ReturnBooksEvidenceRepository returnBooksEvidenceRepository, RewardEvidenceRepository rewardEvidenceRepository, ScholarshipEvidenceRepository scholarshipEvidenceRepository, SchoolEvidenceRepository schoolEvidenceRepository, StudentWorkEvidenceRepository studentWorkEvidenceRepository, StudyEvidenceRepository studyEvidenceRepository, TestCheatEvidenceRepository testCheatEvidenceRepository, VolunteerEvidenceRepository volunteerEvidenceRepository) {
        this.baseUserEvidenceRepository = baseUserEvidenceRepository;
        this.discreditEvidenceRepository = discreditEvidenceRepository;
        this.educationEvidenceRepository = educationEvidenceRepository;
        this.failEvidenceRepository = failEvidenceRepository;
        this.majorEvidenceRepository = majorEvidenceRepository;
        this.matchEvidenceRepository = matchEvidenceRepository;
        this.paymentEvidenceRepository = paymentEvidenceRepository;
        this.punishmentEvidenceRepository = punishmentEvidenceRepository;
        this.repaymentEvidenceRepository = repaymentEvidenceRepository;
        this.returnBooksEvidenceRepository = returnBooksEvidenceRepository;
        this.rewardEvidenceRepository = rewardEvidenceRepository;
        this.scholarshipEvidenceRepository = scholarshipEvidenceRepository;
        this.schoolEvidenceRepository = schoolEvidenceRepository;
        this.studentWorkEvidenceRepository = studentWorkEvidenceRepository;
        this.studyEvidenceRepository = studyEvidenceRepository;
        this.testCheatEvidenceRepository = testCheatEvidenceRepository;
        this.volunteerEvidenceRepository = volunteerEvidenceRepository;
    }

    public List<String> findEvidencesByItem(UserInfoCheckRecord record) {
        return baseUserEvidenceRepository.findEvidencesByItem(record);
    }

    public List<BaseUserEvidence> findByItem(UserInfoCheckRecord item) {
        return  baseUserEvidenceRepository.findByItem(item);
    }

    public void save(BaseUserEvidence baseUserEvidence) {
        baseUserEvidenceRepository.save(baseUserEvidence);
    }

    public double getVolunteerTime(Long id) {
        return volunteerEvidenceRepository.findLengthById(id);
    }
}
