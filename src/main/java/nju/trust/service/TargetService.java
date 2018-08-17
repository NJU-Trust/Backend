package nju.trust.service;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.LargeTargetRequest;
import nju.trust.payloads.target.SmallTargetRequest;
import nju.trust.payloads.target.TargetInfo;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public interface TargetService {

    TargetInfo getTargetInfo(Long targetId);

    ApiResponse applySmallTarget(SmallTargetRequest request, String username);

    ApiResponse applyLargeTarget(LargeTargetRequest request, String username);

    ApiResponse investTarget(Long targetId, String username);

    ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan);
}
