package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.Default;
import nju.trust.payloads.target.ProjectInformation;
import nju.trust.payloads.target.RepaymentAnalysis;
import nju.trust.payloads.target.SurplusPrediction;
import nju.trust.service.target.ConsumptionAnalysis;
import nju.trust.service.target.RepaymentService;
import nju.trust.service.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/10
 * Description:
 */
@RequestMapping("/loan/repayment")
@RestController
public class RepaymentController {

    private RepaymentService repaymentService;

    private TargetService targetService;

    @RequestMapping("/repay")
    public ApiResponse repay(@NotNull Principal principal, @Valid @NotNull Long targetId, @Valid @NotNull Integer period) {
        return targetService.repay(principal.getName(), targetId, period);
    }

    @GetMapping("/info")
    public ProjectInformation getProjectInformation(@NotNull Long targetId) {
        return repaymentService.getProjectInformation(targetId);
    }

    @GetMapping("/default")
    public List<Default> defaults(Principal principal) {
        String username = principal.getName();
        return repaymentService.getDefaults(username);
    }

    @GetMapping("/analysis")
    public RepaymentAnalysis getRepaymentAnalysis(@NotNull Long targetId) {
        return repaymentService.getRepaymentAnalysis(targetId);
    }

    // 消费修正建议，这里采用消费分析是因为 url 和之前的定义的重复了
    @RequestMapping("/consumptionAnalysis")
    public ConsumptionAnalysis getConsumptionAnalysis(String username) {
        return repaymentService.consumptionAnalysis(username);
    }

    @RequestMapping("/predictSurplus")
    public SurplusPrediction getSurplusPrediction(String username) {
        return repaymentService.surplusPrediction(username);
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void checkForDefaults() {
        repaymentService.checkForDefault();
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    @Autowired
    public void setRepaymentService(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }
}
