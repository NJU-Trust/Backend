package nju.trust.service;

import nju.trust.dao.SearchCriteria;
import nju.trust.dao.TargetRepository;
import nju.trust.dao.TargetSpecification;
import nju.trust.dao.UserRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.LargeTarget;
import nju.trust.entity.target.SmallTarget;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
@Service
public class TargetServiceImpl implements TargetService {

    private static final Logger log = LoggerFactory.getLogger("TargetService");

    private TargetRepository targetRepository;

    private UserRepository userRepository;

    private InvestmentRecordRepository recordRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository,
                             UserRepository userRepository,
                             InvestmentRecordRepository recordRepository) {
        this.targetRepository = targetRepository;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public TargetInfo getTargetInfo(Long targetId) {
        return null;
    }

    @Override
    public ApiResponse applySmallTarget(SmallTargetRequest request, String username) {
        SmallTarget smallTarget = new SmallTarget(request, username);
        return setFileAndSaveTarget(smallTarget, request);
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        LargeTarget largeTarget = new LargeTarget(request, username);
        return setFileAndSaveTarget(largeTarget, request);
    }

    @Override
    public ApiResponse investTarget(Long targetId, String username, Double money) {
        BaseTarget baseTarget = targetRepository.findById(targetId)
                .orElseThrow(NoSuchElementException::new);

        double amountAfter = baseTarget.getCollectedMoney() + money;

        if (baseTarget.getTargetType() == TargetType.SMALL) {
            // Check whether collected money has exceeded maximum amount
            double maximumAmount = ((SmallTarget) baseTarget).getMaximumAmount();
            if (amountAfter > maximumAmount)
                return new ApiResponse(false, "Money is too much");
        } else if (amountAfter > baseTarget.getMoney()) {
            return new ApiResponse(false, "This project has already completed");
        } else if (amountAfter == baseTarget.getMoney()) {
            baseTarget.setTargetState(TargetState.COMPLETED);
        }


        baseTarget.setCollectedMoney(amountAfter);
        targetRepository.save(baseTarget);

        // Add record
        recordRepository.save(new InvestmentRecord(targetId, money, username));
        return ApiResponse.successResponse();
    }

    @Override
    public List<TargetInfo> sortTargets(SortingProperty property) {
        Page<BaseTarget> targets = targetRepository
                .findAll(PageRequest.of(1, 20, new Sort(Sort.Direction.DESC, property.getProperty())));


        // Sort by user's field
        return targets.map(this::convertToTargetInfo).getContent();
    }

    @Override
    public List<TargetInfo> filterTargets(List<SearchCriteria> criteriaList, SortingProperty sortingProperty) {
        List<TargetSpecification> specifications = criteriaList.stream()
                .map(TargetSpecification::new).collect(Collectors.toList());

        // Create specifications for searching
        Specification<BaseTarget> specification = null;
        for (Specification<BaseTarget> s : specifications) {
            if (specification == null)
                specification = Specification.where(s);
            else
                specification = specification.and(s);
        }

        // Execute searching and map the results to TargetInfo
        List<BaseTarget> targets;
        if (specification == null) {
            targets = StreamSupport.stream(targetRepository.findAll(Sort.by(sortingProperty.getProperty())).spliterator(),
                    false).collect(Collectors.toList());
        } else
            targets = targetRepository.findAll(specification,
                    Sort.by(Sort.Direction.DESC, sortingProperty.getProperty()));

        return targets.stream().map(this::convertToTargetInfo).collect(Collectors.toList());
    }

    @Override
    public ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan) {
        return null;
    }

    private ApiResponse setFileAndSaveTarget(BaseTarget target, BasicTargetRequest request) {
        try {
            target.setFiles(request.convertFileToByte());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error occurs when getting bytes from MultiPartFile");
            return ApiResponse.serverGoesWrong();
        }

        targetRepository.save(target);

        return ApiResponse.successResponse();
    }

    private TargetInfo convertToTargetInfo(BaseTarget target) {
        return target.getTargetType() == TargetType.SMALL ?
                new SmallTargetInfo((SmallTarget) target) : new LargeTargetInfo((LargeTarget) target);
    }
}
