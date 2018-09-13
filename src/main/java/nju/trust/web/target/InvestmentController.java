package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.service.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/loan/investment")
public class InvestmentController {

    private TargetService targetService;

    @RequestMapping("/target")
    public ApiResponse invest(Long targetId, Principal principal, Double money) {
        return targetService.investTarget(targetId, principal.getName(), money);
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }
}
