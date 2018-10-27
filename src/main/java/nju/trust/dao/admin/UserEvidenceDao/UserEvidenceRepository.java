package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.*;
import nju.trust.entity.record.UserInfoCheckRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private RepaymentEvidenceRepository repaymentEvidenceRepository;
    private ReturnBooksEvidenceRepository returnBooksEvidenceRepository;
    private RewardEvidenceRepository rewardEvidenceRepository;
    private ScholarshipEvidenceRepository scholarshipEvidenceRepository;
    private SchoolEvidenceRepository schoolEvidenceRepository;
    private StudentWorkEvidenceRepository studentWorkEvidenceRepository;
    private StudyEvidenceRepository studyEvidenceRepository;
    private TestCheatEvidenceRepository testCheatEvidenceRepository;
    private VolunteerEvidenceRepository volunteerEvidenceRepository;
    private SelfInfoEvidenceRepository selfInfoEvidenceRepository;
    @Autowired
    public UserEvidenceRepository(BaseUserEvidenceRepository baseUserEvidenceRepository, DiscreditEvidenceRepository discreditEvidenceRepository, EducationEvidenceRepository educationEvidenceRepository, FailEvidenceRepository failEvidenceRepository, MajorEvidenceRepository majorEvidenceRepository, MatchEvidenceRepository matchEvidenceRepository, PaymentEvidenceRepository paymentEvidenceRepository, RepaymentEvidenceRepository repaymentEvidenceRepository, ReturnBooksEvidenceRepository returnBooksEvidenceRepository, RewardEvidenceRepository rewardEvidenceRepository, ScholarshipEvidenceRepository scholarshipEvidenceRepository, SchoolEvidenceRepository schoolEvidenceRepository, StudentWorkEvidenceRepository studentWorkEvidenceRepository, StudyEvidenceRepository studyEvidenceRepository, TestCheatEvidenceRepository testCheatEvidenceRepository, VolunteerEvidenceRepository volunteerEvidenceRepository, SelfInfoEvidenceRepository selfInfoEvidenceRepository) {
        this.baseUserEvidenceRepository = baseUserEvidenceRepository;
        this.discreditEvidenceRepository = discreditEvidenceRepository;
        this.educationEvidenceRepository = educationEvidenceRepository;
        this.failEvidenceRepository = failEvidenceRepository;
        this.majorEvidenceRepository = majorEvidenceRepository;
        this.matchEvidenceRepository = matchEvidenceRepository;
        this.paymentEvidenceRepository = paymentEvidenceRepository;
        this.repaymentEvidenceRepository = repaymentEvidenceRepository;
        this.returnBooksEvidenceRepository = returnBooksEvidenceRepository;
        this.rewardEvidenceRepository = rewardEvidenceRepository;
        this.scholarshipEvidenceRepository = scholarshipEvidenceRepository;
        this.schoolEvidenceRepository = schoolEvidenceRepository;
        this.studentWorkEvidenceRepository = studentWorkEvidenceRepository;
        this.studyEvidenceRepository = studyEvidenceRepository;
        this.testCheatEvidenceRepository = testCheatEvidenceRepository;
        this.volunteerEvidenceRepository = volunteerEvidenceRepository;
        this.selfInfoEvidenceRepository = selfInfoEvidenceRepository;
    }

    public List<String> findEvidencesByItem(UserInfoCheckRecord record) {
        List<BaseUserEvidence> evidences = baseUserEvidenceRepository.findEvidencesByItem(record);
        List<String> result = new ArrayList<>();
        for(BaseUserEvidence evidence : evidences) {
            result.add(evidence.getEvidence());
        }
        return result;
    }

    public List<BaseUserEvidence> findByItem(UserInfoCheckRecord item) {
        return  baseUserEvidenceRepository.findByItem(item);
    }

    public void save(BaseUserEvidence baseUserEvidence) {
        baseUserEvidenceRepository.save(baseUserEvidence);
    }

    public void save(VolunteerEvidence evidence) {
        volunteerEvidenceRepository.save(evidence);
    }

    public void save(StudentWorkEvidence evidence) {studentWorkEvidenceRepository.save(evidence);}

    public double getVolunteerTime(Long id) {
    	List<VolunteerEvidence> evidences = volunteerEvidenceRepository.findByItemId(id);
    	if(evidences == null || evidences.size() == 0) {
    	    return -1;
        }
        return evidences.get(0).getLength();
    }

    public StudentWorkEvidence findStudentWorkEvidenceByItemId(Long id) {
        List<StudentWorkEvidence> evidences = studentWorkEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new StudentWorkEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public RewardEvidence findRewardEvidenceByItemId(Long id) {
        List<RewardEvidence> evidences = rewardEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new RewardEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public MatchEvidence findMatchEvidenceByItemId(Long id) {
        List<MatchEvidence> evidences = matchEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new MatchEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public ScholarshipEvidence findScholarshipByItemId(Long id) {
        List<ScholarshipEvidence> evidences = scholarshipEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new ScholarshipEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public SchoolEvidence findSchoolByItemId(Long id) {
        List<SchoolEvidence> evidences = schoolEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new SchoolEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public EducationEvidence findEducationByItemId(Long id) {
        List<EducationEvidence> evidences = educationEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new EducationEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public FailEvidence findFailNumByItemId(Long id) {
        List<FailEvidence> evidences = failEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new FailEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public StudyEvidence findStudyByItemId(Long id) {
        List<StudyEvidence> evidences = studyEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new StudyEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public TestCheatEvidence findTestCheatByItemId(Long id) {
        List<TestCheatEvidence> evidences = testCheatEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new TestCheatEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public List<BaseUserEvidence> findBasesByItemId(Long itemId) {
        return baseUserEvidenceRepository.findByItemId(itemId);
    }

    public PaymentEvidence findPaymentByItemId(Long id) {
        List<PaymentEvidence> evidences = paymentEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new PaymentEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public RepaymentEvidence findRepaymentByItemId(Long id) {
        List<RepaymentEvidence> evidences = repaymentEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new RepaymentEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public ReturnBooksEvidence findReturnBooksByItemId(Long id) {
        List<ReturnBooksEvidence> evidences = returnBooksEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new ReturnBooksEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public DiscreditEvidence findDiscreditByItemId(Long id) {
        List<DiscreditEvidence> evidences = discreditEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new DiscreditEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public MajorEvidence findMajorByItemId(Long id) {
        List<MajorEvidence> evidences = majorEvidenceRepository.findByItemId(id);
        if(evidences == null || evidences.size() == 0) {
            return new MajorEvidence();
        }else {
            return evidences.get(0);
        }
    }

    public List<SchoolEvidence> findSchoolEvidenceByUser(String username) {
        return schoolEvidenceRepository.findDistinctByUserUsername(username);
    }

    public List<MajorEvidence> findMajorEvidenceByUser(String username) {
        return majorEvidenceRepository.findDistinctByUserUsername(username);
    }

    public List<EducationEvidence> findEducationEvidenceByUser(String username) {
        return educationEvidenceRepository.findDistinctByUserUsername(username);
    }

    public List<FailEvidence> findFailEvidenceByUser(String username) {
        return failEvidenceRepository.findDistinctByUserUsername(username);
    }

    public List<StudyEvidence> findStudyEvidenceByUser(String username) {
        return studyEvidenceRepository.findDistinctByUserUsername(username);
    }

    public List<SelfInfoEvidence> findSelfInfoEvidenceByItem(UserInfoCheckRecord checkRecord) {
        return selfInfoEvidenceRepository.findDistinctByItem(checkRecord);
    }
}
