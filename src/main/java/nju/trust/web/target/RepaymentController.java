package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.service.target.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

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

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkForDefaults() {
        repaymentService.checkForDefault();
    }

    @Autowired
    public void setRepaymentService(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }
}
