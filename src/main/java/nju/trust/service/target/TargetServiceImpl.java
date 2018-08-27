package nju.trust.service.target;

import nju.trust.dao.target.*;
import nju.trust.dao.UserRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.entity.CreditRating;
import nju.trust.entity.TargetRating;
import nju.trust.entity.TargetState;
import nju.trust.entity.TargetType;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.LargeTarget;
import nju.trust.entity.target.SmallTarget;
import nju.trust.entity.user.AssetStatistics;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.*;
import nju.trust.service.TargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
@Service
public class TargetServiceImpl implements TargetService {

    private static final Logger log = LoggerFactory.getLogger("TargetService");

    private static final int RECOMMENDATION_NUMBER = 8''

    private TargetRepository targetRepository;

    private SmallTargetRepository smallTargetRepository;

    private LargeTargetRepository largeTargetRepository;

    private UserRepository userRepository;

    private InvestmentRecordRepository recordRepository;

    public TargetServiceImpl(TargetRepository targetRepository,
                             SmallTargetRepository smallTargetRepository,
                             LargeTargetRepository largeTargetRepository,
                             UserRepository userRepository,
                             InvestmentRecordRepository recordRepository) {
        this.targetRepository = targetRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userRepository = userRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public TargetInfo getTargetInfo(Long targetId) {
        return null;
    }

    @Override
    public ApiResponse applySmallTarget(SmallTargetRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(InternalError::new);
        SmallTarget smallTarget = new SmallTarget(request, user);
        return setFileAndSaveTarget(smallTarget, request);
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(InternalError::new);
        LargeTarget largeTarget = new LargeTarget(request, user);
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
            baseTarget.setTargetState(TargetState.IN_THE_PAYMENT);
        }


        baseTarget.setCollectedMoney(amountAfter);
        targetRepository.save(baseTarget);

        // Add record
        recordRepository.save(new InvestmentRecord(targetId, money, username));
        return ApiResponse.successResponse();
    }

    @Override
    public List<TargetInfo> filterLargeTargets(Pageable pageable, LargeTargetFilterRequest filterRequest) {
        // Execute searching and map the results to TargetInfo
        Specification<LargeTarget> specification = new LargeTargetSpecification(filterRequest);
        Page<LargeTarget> targets = largeTargetRepository.findAll(specification, pageable);

        return targets.stream().map(LargeTargetInfo::new).collect(Collectors.toList());
    }

    @Override
    public List<TargetInfo> filterSmallTargets(Pageable pageable, SmallTargetFilterRequest filterRequest) {
        Specification<SmallTarget> specification = new SmallTargetSpecification(filterRequest);
        Page<SmallTarget> targets = smallTargetRepository.findAll(specification, pageable);

        return targets.stream().map(SmallTargetInfo::new).collect(Collectors.toList());
    }

    public List<TargetInfo> recommendSmallTargets(SmallTargetFilterRequest filterRequest) {
        // Get top 8 targets with highest success rate
        Specification<SmallTarget> specification = new SmallTargetSpecification(filterRequest);
        Page<SmallTarget> targets = smallTargetRepository
                .findAll(specification, PageRequest.of(0, RECOMMENDATION_NUMBER));

        return null;
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

        setTargetRating(target);
        targetRepository.save(target);

        return ApiResponse.successResponse();
    }

    private TargetInfo convertToTargetInfo(BaseTarget target) {
        return target.getTargetType() == TargetType.SMALL ?
                new SmallTargetInfo((SmallTarget) target) : new LargeTargetInfo((LargeTarget) target);
    }

    /**
     * 标的风险评定
     *
     * @param target 目标标的
     */
    private void setTargetRating(BaseTarget target) {
        User user = target.getUser();

        double money = target.getMoney();
        double interestRate = target.getInterestRate();
        int duration = target.getRepaymentDuration();


        CreditRating creditRating = CreditRating.of(user.getCreditScore());
        AssetStatistics assetStatistics = user.getAssetStatistics();
        int monthIncome = assetStatistics.getMonthIncomeLevel();

        // Count number of success
        long numberOfSuccess = targetRepository.count((Specification<BaseTarget>)
                (root, criteriaQuery, criteriaBuilder) -> {
                    Predicate p = criteriaBuilder.equal(root.get("targetState"), TargetState.IN_THE_PAYMENT);
                    return criteriaBuilder.or(p, criteriaBuilder.equal(root.get("targetState"), TargetState.PAY_OFF));
                });

        // Calculate z-value
        double z = 1.18 - 0.35 * money * 0.001 - 0.82 * interestRate + 1.84 * creditRating.getLevel()
                + 0.04 * duration + 0.48 * monthIncome + 1.96 * numberOfSuccess;

        double p = 1. / (1 + Math.exp(-z));
        target.setTargetRating(TargetRating.of(p));
        target.setTargetRatingScore(z);
    }

    private double gaussianKernel(double t) {
        return (1 / Math.sqrt(2 * Math.PI)) * Math.exp(-1 / 2 * Math.pow(t, 2.));
    }
}