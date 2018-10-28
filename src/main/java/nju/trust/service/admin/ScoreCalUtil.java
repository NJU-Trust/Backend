package nju.trust.service.admin;

import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckState;
import nju.trust.entity.UserLevel;
import nju.trust.entity.record.UserEvidence.*;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.RoleName;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import nju.trust.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: 许杨
 * @Description: 个人管理
 * @Date: 2018/9/3
 */
@Service
public class ScoreCalUtil {
    private UserEvidenceRepository userEvidenceRepository;
    private UnstructuredDataRepository unstructuredDataRepository;
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    private UserRepository userRepository;
    @Autowired
    public ScoreCalUtil(UserEvidenceRepository userEvidenceRepository, UnstructuredDataRepository unstructuredDataRepository, UserInfoCheckRecordRepository userInfoCheckRecordRepository, UserRepository userRepository) {
        this.userEvidenceRepository = userEvidenceRepository;
        this.unstructuredDataRepository = unstructuredDataRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
        this.userRepository = userRepository;
    }

    // 计算得分并在后台更新
    public void calScore(UserInfoCheckRecord checkRecord) {
        // 修改状态为approve
        checkRecord.setCheckState(CheckState.PASS);
        userInfoCheckRecordRepository.save(checkRecord);

        switch (checkRecord.getCheckItem()) {
            case VOLUNTEERTIME:
                calVolunteerScore(checkRecord);
                break;
            case STUDENTWORK:
                calStudentWorkScore(checkRecord);
                break;
            case REWARD:
                calRewardScore(checkRecord);
                break;
            case MATCH:
                calMatchScore(checkRecord);
                break;
            case SCHOLARSHIP:
                calScholarshipScore(checkRecord);
                break;
            case SCHOOLTYPE:
                calSchoolTypeScore(checkRecord);
                break;
            case MAJOR:
                calMajorScore(checkRecord);
                break;
            case EDUCATION:
                calEducationScore(checkRecord);
                break;
            case FAILNUM:
                calFailNumScore(checkRecord);
                break;
            case STUDY:
                calStudyScore(checkRecord);
                break;
            case DISCREDIT:
                calDiscreditScore(checkRecord);
                break;
            case TESTCHEAT:
                calTestCheatScore(checkRecord);
                break;
            case PAYMENT:
                calPaymentScore(checkRecord);
                break;
            case REPAYMENT:
                calRepaymentScore(checkRecord);
                break;
            case RETURNBOOKS:
                calReturnBooksScore(checkRecord);
                break;
            case SELFINFO:
                upLevel(checkRecord);
        }

        calSchoolScore(checkRecord.getUser().getUsername());

        changeBaseUserEvidenceState(checkRecord.getId(), CheckState.PASS);
    }
    // 计算校园表现
    private void calSchoolScore(String username) {
        List<UnstructuredData> unstructuredDataList = unstructuredDataRepository.findDistinctByUserUsername(username);
        double scoreSum = 0;

        for(UnstructuredData unstructuredData : unstructuredDataList) {
            double score = unstructuredData.getScore();
            UnstructuredDataType type = unstructuredData.getDataType();
            scoreSum = scoreSum + score * type.getRatio();
        }

        User user = userRepository.findByUsername(username).get();
        user.setCampusScore(scoreSum);
        userRepository.save(user);
    }

    // 用户信息审核：账户升级为中级，信息存入user，unstructuredData中
    private void upLevel(UserInfoCheckRecord checkRecord) {
        List<SelfInfoEvidence> selfInfoEvidences = userEvidenceRepository.findSelfInfoEvidenceByItem(checkRecord);
        for(SelfInfoEvidence evidence : selfInfoEvidences) {
            switch (evidence.getEvidenceType()) {
                case STUDENTCARD:   // 学生证
                    saveStudentCard(evidence);
                    break;
                case SCHOOLCARD:    // 校园卡
                    saveSchoolCard(evidence);
                    break;
                case EDUCATION: // 学历
                    saveEducation(evidence);
                    break;
            }
        }

        // 账户升级
        String username = checkRecord.getUser().getUsername();
        User user = userRepository.findByUsername(username).get();
        Set<RoleName> roleNameSet = user.getRoles();
        ArrayList<RoleName> roleNameArrayList = new ArrayList<>(roleNameSet);
        int index = roleNameArrayList.indexOf(RoleName.ROLE_PRIMARY);
        if(index >= 0) {
            roleNameArrayList.remove(index);
        }
        roleNameArrayList.add(RoleName.ROLE_INTERMEDIATE);
        user.setRoles(roleNameArrayList);
        userRepository.save(user);
    }
    // 学生证截图存入user中
    private void saveStudentCard(SelfInfoEvidence evidence) {
        User user = evidence.getUser();
        user.setStuCardImage(evidence.getEvidence());
        userRepository.save(user);
    }
    // 校园卡截图存入user中
    private void saveSchoolCard(SelfInfoEvidence evidence) {
        User user = evidence.getUser();
        user.setSchoolCardImage(evidence.getEvidence());
        userRepository.save(user);
    }
    // 学历存入非结构化数据中
    private void saveEducation(SelfInfoEvidence evidence) {
        String username = evidence.getUser().getUsername();
        UnstructuredDataType dataType = UnstructuredDataType.EDUCATION;
        UnstructuredData data = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, dataType);
        if(data == null) {
            data = new UnstructuredData();
            data.setDataType(dataType);
            data.setUser(evidence.getUser());
        }
        data.setEvidence(evidence.getEvidence());
        data.setScore(0.0);
        UserInfoCheckRecord item = evidence.getItem();
        data.setDescription(item.getDescription());
        unstructuredDataRepository.save(data);
    }
    // 计算每年平均志愿活动时长加分
    private void calVolunteerScore(UserInfoCheckRecord checkRecord) {
        Long id = checkRecord.getId();
        String username = checkRecord.getUser().getUsername();
        double time = userEvidenceRepository.getVolunteerTime(id);
        double score = time * 1.2;
        UnstructuredData pre = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, UnstructuredDataType.SOCIALITY);
        if(pre == null) {
            pre = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.SOCIALITY);
        }
        double preScore = pre.getScore();
        score = checkScore(0, score);
        pre.setScore(score);
        unstructuredDataRepository.save(pre);
    }
    // 学生工作
    private void calStudentWorkScore(UserInfoCheckRecord checkRecord) {
        Long id = checkRecord.getId();
        StudentWorkType workType = userEvidenceRepository.findStudentWorkEvidenceByItemId(id).getType();
        double score = workType.getScore();
        String username = checkRecord.getUser().getUsername();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.SOCIALITY);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.SOCIALITY);
        }
        score = checkScore(preData.getScore(), score);
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 奖励
    private void calRewardScore(UserInfoCheckRecord checkRecord) {
        Long id = checkRecord.getId();
        String username = checkRecord.getUser().getUsername();
        RewardType type = userEvidenceRepository.findRewardEvidenceByItemId(id).getType();
        double score = type.getScore();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.AWARD);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.AWARD);
        }
        score = checkScore(preData.getScore(), score);
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 科研竞赛获奖情况
    private void calMatchScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        BonusType type = userEvidenceRepository.findMatchEvidenceByItemId(id).getType();
        double score = type.getScore();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.AWARD);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.AWARD);
        }
        score = checkScore(preData.getScore(), score);
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 奖学金
    private void calScholarshipScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        BonusType type = userEvidenceRepository.findScholarshipByItemId(id).getType();
        double score = type.getScore();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.AWARD);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.AWARD);
        }
        score = checkScore(preData.getScore(), score);
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 学校分类
    private void calSchoolTypeScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        SchoolType type = userEvidenceRepository.findSchoolByItemId(id).getSchoolType();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.SCHOOL);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.SCHOOL);
        }
        preData.setScore(type.getScore());
        unstructuredDataRepository.save(preData);
    }
    // 专业分类
    private void calMajorScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        MajorType type = userEvidenceRepository.findMajorByItemId(id).getMajorType();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.MAJOR);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.MAJOR);
        }
        preData.setScore(0.0);
        unstructuredDataRepository.save(preData);
    }
    // 受教育情况
    private void calEducationScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        EducationType type = userEvidenceRepository.findEducationByItemId(id).getEducationType();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.EDUCATION);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.EDUCATION);
        }
        preData.setScore(type.getScore());
        unstructuredDataRepository.save(preData);
    }
    // 挂科数
    private void calFailNumScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findFailNumByItemId(id).getNum();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.FAILED_SUBJECTS);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.FAILED_SUBJECTS);
        }
        preData.setScore(calFailScore(num));
        unstructuredDataRepository.save(preData);
    }
    private double calFailScore(int num) {
        if(num == 0) {
            return 100;
        }else if(num <= 3) {
            return 60;
        }else if(num <= 5) {
            return 20;
        }else {
            return 0;
        }
    }
    // 学习成绩
    private void calStudyScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        double ranking = userEvidenceRepository.findStudyByItemId(id).getRanking();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.GRADE);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.GRADE);
        }
        preData.setScore(calStudyRankScore(ranking));
        unstructuredDataRepository.save(preData);
    }
    private double calStudyRankScore(double ranking) {
        return 100*(1-ranking);
    }
    // 是否为失信人员
    private void calDiscreditScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findDiscreditByItemId(id).getNum();
        if(num > 0) {
            // 为失信人员，无法再进行任何操作
            User user = userRepository.findByUsername(username).get();
            user.setUserLevel(UserLevel.DISCREDIT);
            userRepository.save(user);
        }
    }
    // 考试作弊
    private void calTestCheatScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findTestCheatByItemId(id).getNum();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.VIOLATION);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.VIOLATION);
        }
        double score = preData.getScore() - 100 * num;
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 学费及住宿费的缴纳情况
    private void calPaymentScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findPaymentByItemId(id).getNum();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.VIOLATION);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.VIOLATION);
        }
        double score = preData.getScore() - 50 * num;
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 贷款偿还
    private void calRepaymentScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findRepaymentByItemId(id).getNum();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.VIOLATION);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.VIOLATION);
        }
        double score = preData.getScore() - 100 * num;
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 图书馆借阅还书情况
    private void calReturnBooksScore(UserInfoCheckRecord checkRecord) {
        String username = checkRecord.getUser().getUsername();
        Long id = checkRecord.getId();
        int num = userEvidenceRepository.findReturnBooksByItemId(id).getNum();
        UnstructuredData preData = getUnstructuredData(username, UnstructuredDataType.VIOLATION);
        if(preData == null) {
            preData = createUnstructuredData(checkRecord.getUser(), UnstructuredDataType.VIOLATION);
        }
        double score = preData.getScore() - 10 * num;
        preData.setScore(score);
        unstructuredDataRepository.save(preData);
    }
    // 检查分数 0 <= score <= 100
    private double checkScore(double preScore, double addScore) {
        double score = preScore + addScore;
        if(score > 100) {
            return 100;
        }else if(score < 0) {
            return 0;
        }else {
            return score;
        }
    }

    // 将baseUserEvidence状态修改为pass
    private void changeBaseUserEvidenceState(Long itemId, CheckState state) {
        List<BaseUserEvidence> evidences = userEvidenceRepository.findBasesByItemId(itemId);
        for(BaseUserEvidence evidence : evidences) {
            evidence.setState(state);
            userEvidenceRepository.save(evidence);
        }
    }
    // 获得非结构化数据
    public UnstructuredData getUnstructuredData(String username, UnstructuredDataType dataType) {
        // 检查unstructured_data是否存在，若无则初始化
        UnstructuredData data = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, dataType);
        if(data == null) {
            data = new UnstructuredData();
            data.setDataType(dataType);
            data.setScore(dataType.getInitialScore());
            User user = userRepository.findByUsername(username).get();
            data.setUser(user);
            unstructuredDataRepository.save(data);
            data = unstructuredDataRepository.findFirstByUserUsernameAndDataType(username, dataType);
        }
        return data;
    }

    public CheckState checkUserState(String username) {
        List<UserInfoCheckRecord> records = userInfoCheckRecordRepository.findByUserUsername(username);
        List<CheckState> states = new ArrayList<>();
        for(int i = 0 ; i < records.size() ; i++) {
            states.add(records.get(i).getCheckState());
        }
        if(states.contains(CheckState.PASS)) {
            return CheckState.PASS;
        }
        if(states.contains(CheckState.UPDATE)) {
            return CheckState.UPDATE;
        }
        if(states.contains(CheckState.REJECT)) {
            return CheckState.REJECT;
        }
        return CheckState.ONGING;
    }

    private UnstructuredData createUnstructuredData(User user, UnstructuredDataType type) {
        UnstructuredData preData = new UnstructuredData();
        preData.setUser(user);
        preData.setDataType(type);
        preData.setScore(type.getInitialScore());
        return preData;
    }
}
