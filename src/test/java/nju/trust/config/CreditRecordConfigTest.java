package nju.trust.config;

import nju.trust.dao.user.CreditRecordRepository;
import nju.trust.dao.user.UserRepository;
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
 * @Date: 2018/10/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CreditRecordConfigTest {
    @Autowired
    private CreditRecordRepository creditRecordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CreditRecordConfig test = new CreditRecordConfig(creditRecordRepository, userRepository);

    @Test
    public void saveCredits() {
        test.saveCredits();
    }
}