package nju.trust.web.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.TaskInfo;
import nju.trust.service.lostfound.LostAndFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lostFound")
public class LostAndFoundController {

    private LostAndFoundService lostAndFoundService;

    /**
     * @author: 唐佳未
     * @description: 发布消息
     * @param: taskInfo见payloads/lostfound/TaskInfo
     * @return: payloads/ApiResponse
     * @exception:
     */
    @PostMapping("/launch")
    public ApiResponse launchTask(TaskInfo taskInfo){
        return lostAndFoundService.launchTask(taskInfo);
    }

    @Autowired
    public void setLostAndFoundService(LostAndFoundService lostAndFoundService){
        this.lostAndFoundService = lostAndFoundService;
    }
}
