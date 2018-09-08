package nju.trust;

import nju.trust.dao.admin.UnstructuredDataRepository;
import nju.trust.dao.admin.UserEvidenceDao.DiscreditEvidenceRepository;
import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserEvidence.DiscreditEvidence;
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
public class test {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UnstructuredDataRepository unstructuredDataRepository;
    @Autowired
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    @Autowired
    private DiscreditEvidenceRepository discreditEvidenceRepository;

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

   /* @Test
    // 建立UserInfoCheckRecord
    public void createUserInfoCheckRecord() {
        UserInfoCheckRecord record = new UserInfoCheckRecord();
        record.setCheckItem(CheckItem.DISCREDIT);
        User user = userRepository.findByUsername("weiwei").get();
        record.setUser(user);
        record.setCheckState(CheckState.ONGING);
        record.setTime(LocalDateTime.now());

        userInfoCheckRecordRepository.save(record);
    }*/

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
    }

}
