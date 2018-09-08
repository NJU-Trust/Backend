package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.DiscreditEvidence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DiscreditEvidenceRepositoryTest {
    @Autowired
    private DiscreditEvidenceRepository test;

    @Test
    public void findAll() {
        List<DiscreditEvidence> list = (List<DiscreditEvidence>)test.findAll();
        if(list == null) {
            System.out.println("list == null");
        }else if(list.size() == 0) {
            System.out.println("list.size() == 0");
        }else {
            System.out.println("list.size() == " + list.size());
        }
    }
}