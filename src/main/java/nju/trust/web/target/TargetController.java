package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.LargeTargetRequest;
import nju.trust.payloads.target.SmallTargetRequest;
import nju.trust.service.TargetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
@RestController
@RequestMapping("/target")
public class TargetController {

    private TargetService targetService;

    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping("/new/small")
    public ApiResponse createSmallTarget(SmallTargetRequest smallTargetRequest, Principal principal) {
        System.out.println(smallTargetRequest);
        return targetService.applySmallTarget(smallTargetRequest, principal.getName());
    }

    @PostMapping("/new/large")
    public ApiResponse createLargeTarget(LargeTargetRequest largeTargetRequest, Principal principal) {
        System.out.println(largeTargetRequest);
        return targetService.applyLargeTarget(largeTargetRequest, principal.getName());
    }
}
