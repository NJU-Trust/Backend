package nju.trust.service.lostfound;

import nju.trust.payloads.lostfound.MsgProperty;
import nju.trust.payloads.lostfound.ProcessState;
import nju.trust.payloads.lostfound.TaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LostAndFoundServiceTest {

    private LostAndFoundService lostAndFoundService;

    @Autowired
    public void setLostAndFoundService(LostAndFoundService lostAndFoundService){
        this.lostAndFoundService = lostAndFoundService;
    }

    @Test
    public void getMyTask() {
        List<TaskInfo> list =
                lostAndFoundService.getMyTask("test",MsgProperty.FOUND, ProcessState.DOING);
        for(int i=0;i<list.size();i++)
            list.get(i).print();
    }

    @Test
    public void findTask() {
    }
}