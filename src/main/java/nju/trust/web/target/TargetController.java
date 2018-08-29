package nju.trust.web.target;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.investment.InterestRateInterval;
import nju.trust.payloads.investment.InvestmentStrategy;
import nju.trust.payloads.target.*;
import nju.trust.service.TargetService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
@RestController
@RequestMapping("/loan")
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

    @RequestMapping("/interestRate")
    public InterestRateInterval getInterestRateInterval() {
        return null;
    }

    @RequestMapping("/largeTargetList")
    public List<TargetInfo> getLargeTargets(Pageable pageable, @Valid LargeTargetFilterRequest filter) {
        return targetService.filterLargeTargets(pageable, filter);
    }

    @RequestMapping("/smallTargetList")
    public List<TargetInfo> getSmallTargets(Pageable pageable, @Valid SmallTargetFilterRequest filter) {
        return targetService.filterSmallTargets(pageable, filter);
    }

    @RequestMapping("/recommendSmall")
    public List<TargetInfo> smallTargetRecommendation(@Valid SmallTargetFilterRequest filterRequest) {
        return targetService.recommendSmallTargets(filterRequest);
    }

    @RequestMapping("/recommendStrategy")
    public List<InvestmentStrategy> getRecommendationStrategy(List<Long> targets,
                                                              Double expectedInterestRate, Double money) {
        return targetService.recommendStrategy(targets, money, expectedInterestRate);
    }
}
