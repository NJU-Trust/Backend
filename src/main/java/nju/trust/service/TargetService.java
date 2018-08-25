package nju.trust.service;

import nju.trust.dao.SearchCriteria;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.LargeTargetRequest;
import nju.trust.payloads.target.SmallTargetRequest;
import nju.trust.payloads.target.TargetInfo;

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

    ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan);

    List<TargetInfo> sortTargets(SortingProperty property);

    List<TargetInfo> filterTargets(List<SearchCriteria> criteriaList, SortingProperty sortingProperty);
}
