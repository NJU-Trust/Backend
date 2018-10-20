package nju.trust.web.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.MsgProperty;
import nju.trust.payloads.lostfound.ProcessState;
import nju.trust.payloads.lostfound.TaskInfo;
import nju.trust.service.lostfound.LostAndFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("check")
    public List<TaskInfo> getMyTask(String username, String property, String state){
        return lostAndFoundService.getMyTask(username, MsgProperty.getMsgProperty(property), ProcessState.getProcessState(state));
    }

    @Autowired
    public void setLostAndFoundService(LostAndFoundService lostAndFoundService){
        this.lostAndFoundService = lostAndFoundService;
    }
}
