package nju.trust.dao.admin;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
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
 * @Date: 2018/9/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UnstructuredDataRepositoryTest {
    @Autowired
    private UnstructuredDataRepository unstructuredDataRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findFirstByUserUsernameAndDataTypeTest() {
        User user = userRepository.findByUsername("weiwei").get();

    }
}