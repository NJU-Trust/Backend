package nju.trust.service;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.investment.InvestmentStrategy;
import nju.trust.payloads.target.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public interface TargetService {

    TargetInfo getTargetInfo(Long targetId);

    ApiResponse applySmallTarget(SmallTargetRequest request, String username);

    ApiResponse applyLargeTarget(LargeTargetRequest request, String username);

    ApiResponse investTarget(Long targetId, String username, Double money);

    List<TargetInfo> filterLargeTargets(Pageable pageable, LargeTargetFilterRequest filterRequest);

    List<TargetInfo> filterSmallTargets(Pageable pageable, SmallTargetFilterRequest filterRequest);

    List<TargetInfo> recommendSmallTargets(SmallTargetFilterRequest filterRequest);

    List<InvestmentStrategy> recommendStrategy(List<Long> targetIds, double money, double interestRate);

    ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan);

}
