package nju.trust.service.admin;

import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ScoreCalUtilTest {
    @Autowired
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    @Autowired
    private ScoreCalUtil test;

    @Test
    // 志愿时长
    public void calScore1() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)4).get();
        test.calScore(record);
    }
}