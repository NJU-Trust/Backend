package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.investment.CollectedTarget;
import nju.trust.payloads.investment.CompletedTarget;
import nju.trust.payloads.investment.InvestmentTarget;
import nju.trust.payloads.target.TargetFilter;
import nju.trust.service.target.TargetManagementService;
import nju.trust.service.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/loan/investment")
public class InvestmentController {

    private TargetService targetService;

    private TargetManagementService managementService;

    @RequestMapping("/target")
    public ApiResponse invest(Long targetId, Principal principal, Double money) {
        return targetService.investTarget(targetId, principal.getName(), money);
    }

    @RequestMapping("/ongoing")
    public List<InvestmentTarget> ongoingTargets(Principal principal, TargetFilter filter) {
        return managementService.investedOngoingTargets(principal.getName(), filter);
    }

    @RequestMapping("/complete")
    public List<CompletedTarget> completedTargets(Principal principal, TargetFilter filter) {
        return managementService.investedCompletedTargets(principal.getName(), filter);
    }


    // Perhaps no need to implement this
    @RequestMapping("/collect")
    public List<CollectedTarget> collectedTargets(Principal principal) {
        return null;
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    @Autowired
    public void setManagementService(TargetManagementService managementService) {
        this.managementService = managementService;
    }
}
