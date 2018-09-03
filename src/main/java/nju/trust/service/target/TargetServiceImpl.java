package nju.trust.service.target;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.target.*;
import nju.trust.dao.user.UserMonthlyStatisticsRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.dao.user.UserTotalStatisticsRepository;
import nju.trust.entity.CreditRating;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.*;
import nju.trust.entity.user.*;
import nju.trust.exception.InternalException;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.Range;
import nju.trust.payloads.investment.InvestmentStrategy;
import nju.trust.payloads.target.*;
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



    private TargetRepository targetRepository;

    private SmallTargetRepository smallTargetRepository;

    private LargeTargetRepository largeTargetRepository;

    private UserRepository userRepository;

    private UserMonthlyStatisticsRepository monthlyStatisticsRepository;

    private InvestmentRecordRepository recordRepository;

    private RepaymentRepository repaymentRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository,
                             SmallTargetRepository smallTargetRepository,
                             LargeTargetRepository largeTargetRepository,
                             UserRepository userRepository,
                             UserMonthlyStatisticsRepository monthlyStatisticsRepository,
                             InvestmentRecordRepository recordRepository,
                             RepaymentRepository repaymentRepository) {
        this.targetRepository = targetRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userRepository = userRepository;
        this.monthlyStatisticsRepository = monthlyStatisticsRepository;
        this.recordRepository = recordRepository;
        this.repaymentRepository = repaymentRepository;
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

        return targets.stream().map(SmallTargetInfo::new).collect(Collectors.toList());
    }

    @Override
    public List<InvestmentStrategy> recommendStrategy(List<Long> targetIds, double money, double interestRate) {
        List<SmallTarget> targets = targetIds.stream()
                .map(id -> smallTargetRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("target", "targetId", id)))
                .collect(Collectors.toList());

        return new InvestmentRecommendation(targets, money, interestRate).recommend();
    }

    @Override
    public ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan) {
        return null;
    }

    @Override
    public Range<Double> getLoanTimeRange(String username, double money) {
        List<UserMonthStatistics> statistics = monthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.ASC, "date"));

        UserMonthlyDataHelper helper = new UserMonthlyDataHelper(statistics);
        double k = helper.forecastSurplus();
        double a = helper.forecastDisc();
        double k0 = helper.getTotalSurplus(); // Total surplus
        double a0 = helper.getTotalDisc();

        if (money <= k0)
            return new Range<>(0., 1.);

        double upper = Math.min(12., Math.ceil((money - k0) / k));
        double lower = Math.min(12., Math.ceil((money - k0 - a0) / (k + a)));

        return new Range<>(lower, upper);
    }

    @Override
    public Double getRemainingAmount(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Repayment> repayments = repaymentRepository.findAllByUserUsername(username);
        return user.getCreditRating().getBorrowingAmount() -
                repayments.stream().mapToDouble(Repayment::getRemainingAmount).sum();
    }

    @Override
    public RepaymentTotalInfo getRepaymentInfo(String username, RepaymentType type, double principal,
                                               double duration, double interestRate) {

        RepaymentCalculator calculator;

        switch (type) {
            case EQUAL_PRINCIPAL:
                calculator = new EPRepaymentCalculator(principal, duration, interestRate);
                break;
            case PRE_INTEREST:
                calculator = new PIRepaymentCalculator(principal, duration, interestRate);
                break;
            case ONE_TIME_PAYMENT:
                calculator = new OTPRepaymentCalculator(principal, duration, interestRate);
                break;
            case EQUAL_INSTALLMENT_OF_PRINCIPAL_AND_INTEREST:
                calculator = new EIPIRepaymentCalculator(principal, duration, interestRate);
                break;
            default:
                throw new ResourceNotFoundException("Repayment type not found");
        }

        double totalRepayment = calculator.getTotalRepayment();
        double totalInterest = calculator.getTotalInterest();
        RepaymentNote note = getRepaymentNote(username, calculator.getMonthlyRepayment(),
                duration, totalRepayment);

        return new RepaymentTotalInfo(totalInterest, totalRepayment, calculator.monthlyRepayment, note);
    }

    private RepaymentNote getRepaymentNote(String username, List<Double> monthlyRepayment, double duration, double totalRepayment) {
        // Generate repayment note
        List<UserMonthStatistics> statistics = monthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.ASC, "date"));

        if (statistics.isEmpty()) {
            String message = "User " + username + " doesn't have history data";
            log.error(message);
            throw new ResourceNotFoundException(message);
        }

        UserMonthlyDataHelper helper = new UserMonthlyDataHelper(statistics);

        // Calculate difficulty value
        RepaymentNoteHelper noteHelper = new RepaymentNoteHelper(helper.getTotalSurplus(), helper.getTotalDisc(),
                helper.forecastSurplus(), helper.forecastDisc(), helper.getTotalDebt(),
                duration, totalRepayment, monthlyRepayment);

        // Calculate disc ratios
        UserMonthStatistics latest = statistics.get(statistics.size() - 1);
        List<Double> discRatios = Arrays.asList(latest.getDailyToAll(), latest.getFoodToAll(),
                latest.getTravelToAll(), latest.getFunToAll());

        return new RepaymentNote(noteHelper.evaluateSurplus(), discRatios,
                noteHelper.evaluateDisc(), noteHelper.evaluateDifficulty());
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
