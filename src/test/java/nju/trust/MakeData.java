package nju.trust;

import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.admin.UserEvidenceDao.DiscreditEvidenceRepository;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserEvidence.DiscreditEvidence;
import nju.trust.entity.record.UserEvidence.StudentWorkEvidence;
import nju.trust.entity.record.UserEvidence.StudentWorkType;
import nju.trust.entity.record.UserEvidence.VolunteerEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import nju.trust.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 伪造数据
 * @Date: 2018/9/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MakeData {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UnstructuredDataRepository unstructuredDataRepository;
    @Autowired
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    @Autowired
    private DiscreditEvidenceRepository discreditEvidenceRepository;
    @Autowired
    private UserEvidenceRepository userEvidenceRepository;

/*    @Test
    // 建立UnstructuredData的VIOLATION类型数据
    public void createUnstructureViolation() {
        User user = userRepository.findByUsername("weiwei").get();
        System.out.println("user:"+user.getUsername());

        UnstructuredData unstructuredData = new UnstructuredData();
        unstructuredData.setEvidence("evidence");
        unstructuredData.setScore(0.0);
        unstructuredData.setDescription("description");
        unstructuredData.setUser(user);
        unstructuredData.setDataType(UnstructuredDataType.VIOLATION);

        unstructuredDataRepository.save(unstructuredData);
    }*/
/*    @Test
    // 建立UnstructuredData的VIOLATION类型数据
    public void createUnstructureViolation() {
        User user = userRepository.findByUsername("weiwei").get();
        System.out.println("user:"+user.getUsername());

        UnstructuredData unstructuredData = new UnstructuredData();
        unstructuredData.setEvidence("evidence");
        unstructuredData.setScore(0.0);
        unstructuredData.setDescription("description");
        unstructuredData.setUser(user);
        unstructuredData.setDataType(UnstructuredDataType.VIOLATION);

        unstructuredDataRepository.save(unstructuredData);
    }*/

/*    @Test
    // 建立UserInfoCheckRecord
    public void createUserInfoCheckRecord() {
        UserInfoCheckRecord record = new UserInfoCheckRecord();
        record.setCheckItem(CheckItem.STUDENTWORK);
        User user = userRepository.findByUsername("weiwei").get();
        record.setUser(user);
        record.setCheckState(CheckState.ONGING);
        record.setTime(LocalDateTime.now());

        userInfoCheckRecordRepository.save(record);
    }*/
/*
    @Test
    public void createDiscreditEvidence() {
        User user = userRepository.findByUsername("weiwei").get();

        UserInfoCheckRecord item = userInfoCheckRecordRepository.findById((long)2).get();
        System.out.println("item id:"+item.getId());

        LocalDateTime time = LocalDateTime.now();
        System.out.println("time:"+time);

        CheckState state = CheckState.ONGING;

        String evidence = "evidence";

        DiscreditEvidence discreditEvidence = new DiscreditEvidence(user, item, time, state, evidence);
        discreditEvidenceRepository.save(discreditEvidence);
    }*/
/*
    @Test
    public void createVolunteerEvidence() {
        User user = userRepository.findByUsername("weiwei").get();

        UserInfoCheckRecord item = userInfoCheckRecordRepository.findById((long)4).get();
        System.out.println("item id:"+item.getId());

        LocalDateTime time = LocalDateTime.now();
        System.out.println("time:"+time);

        CheckState state = CheckState.ONGING;

        String evidence = "evidence";

        double length = 20.0;

        VolunteerEvidence volunteerEvidence = new VolunteerEvidence(user, item, time, state, evidence, length);
        userEvidenceRepository.save(volunteerEvidence);
    }*/
/*
    @Test
    public void createStudentWorkEvidence() {
        User user = userRepository.findByUsername("weiwei").get();

        UserInfoCheckRecord item = userInfoCheckRecordRepository.findById((long)13).get();
        System.out.println("item id:"+item.getId());

        LocalDateTime time = LocalDateTime.now();
        System.out.println("time:"+time);

        CheckState state = CheckState.ONGING;

        String evidence = "student work evidence";

        StudentWorkType type = StudentWorkType.WORKER;

        StudentWorkEvidence studentWorkEvidence = new StudentWorkEvidence(user, item, time, state, evidence, type);
        userEvidenceRepository.save(studentWorkEvidence);
    }*/
}
