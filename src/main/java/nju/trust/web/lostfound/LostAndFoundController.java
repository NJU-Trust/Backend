package nju.trust.web.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.*;
import nju.trust.service.lostfound.LostAndFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/lostFound")
public class LostAndFoundController {

    private LostAndFoundService lostAndFoundService;
    @Autowired
    public void setLostAndFoundService(LostAndFoundService lostAndFoundService){
        this.lostAndFoundService = lostAndFoundService;
    }

    @PostMapping("/launch")
    public ApiResponse launchTask(@RequestBody UploadLostAndFoundRequest taskInfo, Principal principal){
        return lostAndFoundService.launchTask(taskInfo, principal.getName());
    }

    @GetMapping("/check")
    public List<TaskInfo> getMyTask(String username, String property, String state){
        return lostAndFoundService.getMyTask(username, MsgProperty.getMsgProperty(property), ProcessState.getProcessState(state));
    }

    @RequestMapping(value = "/getNew")
    public TaskInfoPage getNovelTrade(@Valid @RequestBody LostAndFoundFilterRequest filter) {
        return lostAndFoundService.findTask(filter);
    }

    @GetMapping(value = "/finish")
    public ApiResponse finish(Long id, String toUsername) {
        return lostAndFoundService.submitResult(id, toUsername);
    }

}
