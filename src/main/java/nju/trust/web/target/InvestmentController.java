package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.investment.CollectedTarget;
import nju.trust.payloads.investment.CompletedTarget;
import nju.trust.payloads.investment.InvestmentTarget;
import nju.trust.payloads.target.BadTarget;
import nju.trust.payloads.target.TargetFilter;
import nju.trust.service.target.TargetManagementService;
import nju.trust.service.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/loan/investment")
public class InvestmentController {

    private TargetService targetService;

    private TargetManagementService managementService;

    @RequestMapping("/target")
    public ApiResponse invest(@NotNull Long targetId, Principal principal, @NotNull Double money) {
        return targetService.investTarget(targetId, principal.getName(), money);
    }

    @RequestMapping("/ongoing")
    public List<InvestmentTarget> ongoingTargets(String username, TargetFilter filter) {
        return managementService.investedOngoingTargets(username, filter);
    }

    @RequestMapping("/complete")
    public List<CompletedTarget> completedTargets(String username, TargetFilter filter) {
        return managementService.investedCompletedTargets(username, filter);
    }


    // Perhaps no need to implement this
    @RequestMapping("/collect")
    public List<CollectedTarget> collectedTargets(String username) {
        return null;
    }

    @RequestMapping("/bad")
    public List<BadTarget> getBadTargets(String username, Double moneyUpper, Double moneyLower, String state) {
        return targetService.getBadTargets(username, moneyUpper, moneyLower, state);
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
