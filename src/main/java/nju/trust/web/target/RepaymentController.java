package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.Default;
import nju.trust.payloads.target.ProjectInformation;
import nju.trust.payloads.target.RepaymentAnalysis;
import nju.trust.payloads.target.SurplusPrediction;
import nju.trust.service.target.ConsumptionAnalysis;
import nju.trust.service.target.RepaymentService;
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

    @RequestMapping("/repay")
    public ApiResponse repay(Principal principal, @Valid @NotNull Long targetId, @Valid @NotNull Integer period) {
        return null;
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
    public ConsumptionAnalysis getConsumptionAnalysis(Principal principal) {
        return repaymentService.consumptionAnalysis(principal.getName());
    }

    @RequestMapping("/predictSurplus")
    public SurplusPrediction getSurplusPrediction(Principal principal) {
        return repaymentService.surplusPrediction(principal.getName());
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void checkForDefaults() {
        repaymentService.checkForDefault();
    }

    @Autowired
    public void setRepaymentService(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }
}
