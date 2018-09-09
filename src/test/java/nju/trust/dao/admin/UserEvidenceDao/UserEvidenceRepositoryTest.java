package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserEvidence.VolunteerEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserEvidenceRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    @Autowired
    private UserEvidenceRepository userEvidenceRepository;

    @Test
    // 保存VolunteerEvidence
    public void save() {
        User user = userRepository.findByUsername("weiwei").get();

        UserInfoCheckRecord item = userInfoCheckRecordRepository.findById((long)4).get();
        System.out.println("item id:"+item.getId());

        LocalDateTime time = LocalDateTime.now();
        System.out.println("time:"+time);

        CheckState state = CheckState.ONGING;

        String evidence = "evidence";

        double length = 100.0;

        VolunteerEvidence volunteerEvidence = new VolunteerEvidence(user, item, time, state, evidence, length);
        userEvidenceRepository.save(volunteerEvidence);
    }
}