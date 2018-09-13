package nju.trust.service.target;

import nju.trust.entity.user.RepaymentType;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.Range;
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

    List<TargetInfo> filterLargeTargets(Pageable pageable, LargeTargetFilterRequest filterRequest);

    List<TargetInfo> filterSmallTargets(Pageable pageable, SmallTargetFilterRequest filterRequest);

    List<TargetInfo> recommendSmallTargets(SmallTargetFilterRequest filterRequest);

    List<InvestmentStrategy> recommendStrategy(List<Integer> targetIds, double money, double interestRate);

    Range<Double> getLoanTimeRange(String username, double money);

    Double getRemainingAmount(String username);

    RepaymentTotalInfo getRepaymentInfo(String username, RepaymentType type, double principal,
                                        double duration, double interestRate);

    ConsumptionCorrection getConsumptionCorrection(String username, Long targetId);

    ApiResponse investTarget(Long targetId, String username, Double money);

    ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan);

    ApiResponse repay(String username, Long targetId, Integer period);
}
