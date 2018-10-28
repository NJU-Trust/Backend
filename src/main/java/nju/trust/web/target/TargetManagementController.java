package nju.trust.web.target;

import nju.trust.payloads.target.DefaultRecord;
import nju.trust.payloads.target.OnGoingTarget;
import nju.trust.payloads.target.ReleasedTarget;
import nju.trust.payloads.target.TargetFilter;
import nju.trust.service.target.TargetManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
@RestController
@RequestMapping("loan/info")
public class TargetManagementController {

    private TargetManagementService targetManagementService;

    @RequestMapping("/ongoing")
    public List<OnGoingTarget> onGoingTargets(String username, TargetFilter filter) {
        return targetManagementService.getOnGoingTargetList(username, filter);
    }

    @RequestMapping("/complete")
    public List<ReleasedTarget> completedTargets(String username, TargetFilter filter) {
        return targetManagementService.completedTargetList(username, filter);
    }

    @RequestMapping("/released")
    public List<ReleasedTarget> releasedTargets(String username, TargetFilter filter) {
        return targetManagementService.releasedTargetList(username, filter);
    }

    @RequestMapping("/default")
    public List<DefaultRecord> defaultRecords(String username, TargetFilter filter) {
        return targetManagementService.defaultRecords(username);
    }

    @Autowired
    public void setTargetManagementService(TargetManagementService targetManagementService) {
        this.targetManagementService = targetManagementService;
    }
}
