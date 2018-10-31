package nju.trust.service.target;

import nju.trust.entity.target.BaseTarget;
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

    List<InvestmentHistory> getInvestmentHistory(Long targetId);

    TargetDetails getTargetDetails(Long targetId);

    TargetInfo getTargetInfo(Long targetId);

    ApiResponse applySmallTarget(SmallTargetRequest request, String username);

    ApiResponse applyLargeTarget(LargeTargetRequest request, String username);

    List<TargetInfo> filterLargeTargets(LargeTargetFilterRequest filterRequest);

    List<TargetInfo> filterSmallTargets(SmallTargetFilterRequest filterRequest);

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

    List<BadTarget> getBadTargets(String username, Double moneyUpper, Double moneyLower, String state);

    List<TargetInfo> searchTargets(String name);
}
