package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
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

    @RequestMapping("/repay")
    public ApiResponse repay(Principal principal, @Valid @NotNull Long targetId, @Valid @NotNull Integer period) {
        return null;
    }
}
