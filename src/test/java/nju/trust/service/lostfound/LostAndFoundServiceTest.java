package nju.trust.service.lostfound;

import nju.trust.payloads.lostfound.MsgProperty;
import nju.trust.payloads.lostfound.TaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void launchTask() {
        lostAndFoundService.launchTask(new TaskInfo("test", "失物招领","雨伞","红色雨伞","13899900129","mypicpath","拾到一把红色的雨伞","四五六食堂"));
    }

    @Test
    public void getMyTask() {
    }

    @Test
    public void findTask() {
    }
}