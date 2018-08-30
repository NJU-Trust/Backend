package nju.trust.service.target;

import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.target.*;
import nju.trust.dao.user.UserMonthlyStatisticsRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CreditRating;
import nju.trust.entity.target.TargetRating;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.LargeTarget;
import nju.trust.entity.target.SmallTarget;
import nju.trust.entity.user.User;
import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.exception.InternalException;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.Range;
import nju.trust.payloads.investment.InvestmentStrategy;
import nju.trust.payloads.target.*;
import nju.trust.service.TargetService;
import nju.trust.util.SimpleExponentialSmoothing;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static final int RECOMMENDATION_NUMBER = 8;

    private static final double BANDWIDTH = 0.0274;

    private TargetRepository targetRepository;

    private SmallTargetRepository smallTargetRepository;

    private LargeTargetRepository largeTargetRepository;

    private UserRepository userRepository;

    private UserMonthlyStatisticsRepository monthlyStatisticsRepository;

    private InvestmentRecordRepository recordRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository,
                             SmallTargetRepository smallTargetRepository,
                             LargeTargetRepository largeTargetRepository,
                             UserRepository userRepository,
                             UserMonthlyStatisticsRepository monthlyStatisticsRepository,
                             InvestmentRecordRepository recordRepository) {
        this.targetRepository = targetRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userRepository = userRepository;
        this.monthlyStatisticsRepository = monthlyStatisticsRepository;
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
        return setFileAndSaveTarget(smallTarget);
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(InternalError::new);
        LargeTarget largeTarget = new LargeTarget(request, user);
        return setFileAndSaveTarget(largeTarget);
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
        recordRepository.save(new InvestmentRecord(targetId, money));
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

    @Override
    public List<TargetInfo> recommendSmallTargets(SmallTargetFilterRequest filterRequest) {
        // Get top 8 targets with highest success rate
        Specification<SmallTarget> specification = new SmallTargetSpecification(filterRequest);
        List<SmallTarget> targets = smallTargetRepository
                .findAll(specification, PageRequest.of(0, RECOMMENDATION_NUMBER)).getContent();

        return null;
    }

    @Override
    public List<InvestmentStrategy> recommendStrategy(List<Long> targetIds, double money, double interestRate) {
        List<SmallTarget> targets = targetIds.stream()
                .map(id -> smallTargetRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("target", "targetId", id)))
                .collect(Collectors.toList());

        int matrixSize = targetIds.size();

        // Calculate distance matrix
        RealMatrix distanceMatrix = MatrixUtils.createRealMatrix(matrixSize, matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i + 1; j < matrixSize; j++) {
                double iScore = targets.get(i).getTargetRatingScore();
                double jScore = targets.get(j).getTargetRatingScore();
                double distance = Math.abs(iScore - jScore);
                distanceMatrix.setEntry(i, j, distance);
                distanceMatrix.setEntry(j, i, distance);
            }
        }

        RealMatrix kernelValueMatrix = distanceMatrix.copy();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = i; j < matrixSize; j++) {
                double ijDistance = kernelValueMatrix.getEntry(i, j);
                double kernelValue = gaussianKernel(ijDistance / BANDWIDTH);
                kernelValueMatrix.setEntry(i, j, kernelValue);
                kernelValueMatrix.setEntry(j, i, kernelValue);
            }
        }

        // Calculate weight for each distance
        RealMatrix weightMatrix = MatrixUtils.createRealMatrix(matrixSize, matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double rowSum = sum(kernelValueMatrix.getRow(i));
            for (int j = 0; j < matrixSize; j++) {
                double weight = kernelValueMatrix.getEntry(i, j) / rowSum;
                weightMatrix.setEntry(i, j, weight);
            }
        }

        // Calculate yield
        RealVector yieldVector = new ArrayRealVector(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double yield = 0.;
            for (int j = 0; j < matrixSize; j++) {
                yield += weightMatrix.getEntry(i, j) * targets.get(j).getRepayment().getYearInterestRate();
            }
            yieldVector.setEntry(i, yield);
        }

        RealVector stdDeviation = new ArrayRealVector(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            double sigma = 0.;
            double yield = yieldVector.getEntry(i);
            for (int j = 0; j < matrixSize; j++) {
                sigma += weightMatrix.getEntry(i, j) *
                        Math.pow(targets.get(j).getRepayment().getYearInterestRate() - yield, 2);
            }
            stdDeviation.setEntry(i, Math.sqrt(sigma));
        }

        // Linear programming part
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(stdDeviation, 0);
        List<LinearConstraint> constraints = new ArrayList<>();
        LinearConstraint constraint1 = new LinearConstraint(yieldVector, Relationship.EQ, interestRate);
        LinearConstraint constraint2 = new LinearConstraint(new ArrayRealVector(matrixSize, 1.),
                Relationship.EQ, 1);
        constraints.add(constraint1);
        constraints.add(constraint2);
        for (int i = 0; i < matrixSize; i++) {
            double e = targets.get(i).getMoney() - targets.get(i).getCollectedMoney();
            constraints.add(new LinearConstraint(new double[]{money}, Relationship.LEQ, e));
        }
        SimplexSolver optimizer = new SimplexSolver();
        double[] result = optimizer.optimize(objectiveFunction,
                new LinearConstraintSet(constraints), GoalType.MINIMIZE).getPoint();

        List<InvestmentStrategy> strategies = new ArrayList<>();
        for (int i = 0; i < matrixSize; i++) {
            strategies.add(new InvestmentStrategy(targetIds.get(i), result[i], result[i] * money));
        }

        return strategies;
    }

    @Override
    public ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan) {
        return null;
    }

    @Override
    public Range<Double> getLoanTimeRange(String username, double money) {
        List<UserMonthStatistics> statistics = monthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.ASC, "date"));

        List<Double> surplusData = statistics.stream().map(UserMonthStatistics::getSurplus).collect(Collectors.toList());
        List<Double> discData = statistics.stream().map(UserMonthStatistics::getDisc).collect(Collectors.toList());
        double k0 = SimpleExponentialSmoothing.forecast(surplusData);
        double a0 = SimpleExponentialSmoothing.forecast(discData);

        if (money <= k0)
            return new Range<>(0., 1.);

        double lower = Math.ceil(money / k0);
        if (lower > 12.)
            lower = 12.;
        double upper = Math.min(12., Math.ceil(money / (k0 + a0)));

        return new Range<>(lower, upper);
    }

    private ApiResponse setFileAndSaveTarget(BaseTarget target) {
        setTargetRating(target);
        targetRepository.save(target);

        return ApiResponse.successResponse();
    }

    /**
     * 标的风险评定
     *
     * @param target 目标标的
     */
    private void setTargetRating(BaseTarget target) {
        User user = target.getUser();

        double money = target.getMoney();
        double interestRate = target.getRepayment().getYearInterestRate();
        int duration = target.getRepayment().getRepaymentDuration();

        CreditRating creditRating = CreditRating.of(user.getCreditScore());
        double avgMonthlyIncome = user.getMonthStatistics().stream()
                .mapToDouble(UserMonthStatistics::getIncome).average()
                .orElseThrow(() -> new InternalException("Something is wrong"));
        int monthIncome = getMonthIncomeLevel(avgMonthlyIncome);

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

    private double sum(double[] array) {
        return Arrays.stream(array).sum();
    }

    private int getMonthIncomeLevel(double averagedMonthIncome) {
        double[] slices = {1000., 2000., 3000., 4000., 5000., 10000.};
        int i = 1;
        for (double slice : slices) {
            if (averagedMonthIncome < slice)
                return i;
            i++;
        }

        return i;
    }
}
