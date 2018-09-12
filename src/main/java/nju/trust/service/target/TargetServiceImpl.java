package nju.trust.service.target;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.record.LoanRecordRepository;
import nju.trust.dao.record.RecordDao;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.*;
import nju.trust.dao.user.UserMonthlyStatisticsRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.record.LoanRecord;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.*;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.RepaymentType;
import nju.trust.entity.user.User;
import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.Range;
import nju.trust.payloads.investment.InvestmentStrategy;
import nju.trust.payloads.target.*;
import nju.trust.service.TransferHelper;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private RecordDao recordDao;

    private TargetRepository targetRepository;

    private SmallTargetRepository smallTargetRepository;

    private LargeTargetRepository largeTargetRepository;

    private UserRepository userRepository;

    private UserMonthlyStatisticsRepository monthlyStatisticsRepository;

    private InvestmentRecordRepository investmentRecordRepository;

    private RepaymentRepository repaymentRepository;

    private LoanRecordRepository loanRecordRepository;

    private RepaymentRecordRepository repaymentRecordRepository;

    private TransferHelper transferHelper;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository,
                             SmallTargetRepository smallTargetRepository,
                             LargeTargetRepository largeTargetRepository,
                             UserRepository userRepository,
                             LoanRecordRepository loanRecordRepository,
                             UserMonthlyStatisticsRepository monthlyStatisticsRepository,
                             InvestmentRecordRepository investmentRecordRepository,
                             RecordDao recordDao,
                             RepaymentRepository repaymentRepository,
                             TransferHelper transferHelper,
                             RepaymentRecordRepository repaymentRecordRepository) {
        this.targetRepository = targetRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userRepository = userRepository;
        this.monthlyStatisticsRepository = monthlyStatisticsRepository;
        this.investmentRecordRepository = investmentRecordRepository;
        this.repaymentRepository = repaymentRepository;
        this.repaymentRecordRepository = repaymentRecordRepository;
        this.transferHelper = transferHelper;
        this.recordDao = recordDao;
        this.loanRecordRepository = loanRecordRepository;
    }

    @Override
    public TargetInfo getTargetInfo(Long targetId) {
        return null;
    }

    @Override
    public ApiResponse applySmallTarget(SmallTargetRequest request, String username) {
        User user = getUser(username);

        SmallTarget smallTarget = new SmallTarget(LocalDate.now(), request.getName(),
                request.getMoney(), request.getCompletionRate(), request.getProjectDescription(),
                request.getClassification(), request.getIdentityOption(), user,
                request.getUseOfFunds(), request.getProof());

        settingTarget(smallTarget, request);
        smallTarget = targetRepository.save(smallTarget);
//        recordDao.save(new LoanRecord(user, smallTarget));
        return ApiResponse.successResponse();
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        User user = getUser(username);

        LargeTarget largeTarget = new LargeTarget(LocalDate.now(), request.getName(),
                request.getMoney(), request.getUseOfFunds(), request.getCompletionRate(),
                request.getProjectDescription(), request.getClassification(), user, request.getProof());

        settingTarget(largeTarget, request);

        largeTarget = targetRepository.save(largeTarget);
        recordDao.save(new LoanRecord(user, largeTarget));

        return ApiResponse.successResponse();
    }

    @Override
    public ApiResponse investTarget(Long targetId, String username, Double money) {
        BaseTarget baseTarget = getTarget(targetId);
        User user = getUser(username);

        double amountAfter = baseTarget.getCollectedMoney() + money;

        if (amountAfter > baseTarget.getMoney()) {
            return new ApiResponse(false, "Money too much");
        }

        baseTarget.setCollectedMoney(amountAfter);
        if (baseTarget.tryToSetToInThePayment()) {
            // Transfer money to target owner's account and record
            transferHelper.transferLoanToUserAccount(user, baseTarget.getMoney());
            loanRecordRepository.makeLoanRecordSucceed(targetId);

            // Update all the repaymentRecord
            List<RepaymentRecord> records = repaymentRecordRepository.findAllByTargetId(targetId);
            LocalDate now = LocalDate.now();
            long delta = now.until(baseTarget.getRepayment().getStartDate(), ChronoUnit.DAYS);
            records.forEach(r -> r.setReturnDate(r.getReturnDate().minusDays(delta)));
            repaymentRecordRepository.saveAll(records);

            // Update repayment startTime
            baseTarget.setRepaymentStartDate(now);
        }

        targetRepository.save(baseTarget);
        investmentRecordRepository.save(new InvestmentRecord(user, baseTarget, money));

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
        List<UserMonthStatistics> statistics = getUserMonthStatistics(username);

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
        User user = getUser(username);

        List<Repayment> repayments = repaymentRepository.findAllByUserUsername(username);
        return user.getCreditRating().getBorrowingAmount() -
                repayments.stream().mapToDouble(Repayment::getRemainingAmount).sum();
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public RepaymentTotalInfo getRepaymentInfo(String username, RepaymentType type, double principal,
                                               double duration, double interestRate) {

        RepaymentCalculator calculator = RepaymentCalculator.getCalculator(type, principal, duration, interestRate);

        double totalRepayment = calculator.getTotalRepayment();
        double totalInterest = calculator.getTotalInterest();
        RepaymentNote note = getRepaymentNote(username, calculator.getMonthlyRepayment(),
                duration, totalRepayment);

        return new RepaymentTotalInfo(totalInterest, totalRepayment, calculator.monthlyRepayment, note);
    }

    @Override
    public ConsumptionCorrection getConsumptionCorrection(String username, Long targetId) {
        BaseTarget target = getTarget(targetId);

        // Get monthly repayment information
        List<RepaymentRecord> records = repaymentRecordRepository
                .findAllByTargetId(targetId, Sort.by(Sort.Direction.ASC, "returnDate"));
        List<Double> monthlyRepayment = records.stream().map(RepaymentRecord::getSum).collect(Collectors.toList());

        // Get user statistics
        List<UserMonthStatistics> statistics = getUserMonthStatistics(username);

        ConsumptionCorrectionEvaluator evaluator = new ConsumptionCorrectionEvaluator(statistics,
                target, monthlyRepayment);

        return evaluator.evaluate();
    }

    private BaseTarget getTarget(Long targetId) {
        return targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
    }

    @Override
    public ApiResponse repay(String username, Long targetId, Integer period) {
        RepaymentRecord record = repaymentRecordRepository.findByTargetIdAndPeriod(targetId, period)
                .orElseThrow(() -> new ResourceNotFoundException("Repayment record not found"));

        double money = 0.;
        double serviceCharge = 0.;
        if (record.isPayOff())
            return new ApiResponse(false, "This period has been repaid");
        else if (record.isOverdue()) {
            double principalInterestSum = record.getSum();
            long overdueDays = record.getOverdueDays();
            money = principalInterestSum + FineCalculator.getOverdueFine(principalInterestSum, overdueDays);
            serviceCharge = FineCalculator.getOverdueServiceCharge(principalInterestSum, overdueDays);
        } else if (record.isBeforeSettlementDay()) {
            List<RepaymentRecord> records = repaymentRecordRepository.findAllByTargetId(targetId);
            double prepaymentFine = FineCalculator.getPrepaymentFine(records, period);
            money = record.getSum() + prepaymentFine;
        }

        User borrower = getUser(username);
        if (transferHelper.getRepaymentFromUserAccount(borrower, money)
                && transferHelper.getRepaymentFromUserAccount(borrower, serviceCharge)) {
            record.makeRepaid();
            repaymentRecordRepository.save(record);
            // Calculate investors quota and transfer it to them
            List<InvestmentRecord> investmentRecords = investmentRecordRepository.findAllByTargetId(targetId);
            BaseTarget target = targetRepository.findById(targetId)
                    .orElseThrow(() -> new ResourceNotFoundException("target not found"));
            double collectedMoney = target.getCollectedMoney();
            for (InvestmentRecord i : investmentRecords) {
                double quota = i.getInvestedMoney() / collectedMoney * money;
                transferHelper.repaidToInvestor(borrower, i.getUser(), quota);
            }
        } else {
            return new ApiResponse(false, "User money is not enough");
        }

        return ApiResponse.successResponse();
    }

    private RepaymentNote getRepaymentNote(String username, List<Double> monthlyRepayment, double duration, double totalRepayment) {
        // Generate repayment note
        List<UserMonthStatistics> statistics = getUserMonthStatistics(username);

        if (statistics.isEmpty()) {
            String message = "User " + username + " doesn't have history data";
            log.error(message);
            throw new ResourceNotFoundException(message);
        }

        UserMonthlyDataHelper helper = new UserMonthlyDataHelper(statistics);

        // Calculate difficulty value
        RepaymentNoteHelper noteHelper = new RepaymentNoteHelper(helper.getTotalSurplus(),
                helper.forecastSurplus(), helper.forecastDisc(), helper.getCurrentDebt(),
                duration, totalRepayment, monthlyRepayment);

        // Calculate disc ratios
        UserMonthStatistics latest = statistics.get(statistics.size() - 1);
        List<Double> discRatios = Arrays.asList(latest.getDailyToAll(), latest.getFoodToAll(),
                latest.getTravelToAll(), latest.getFunToAll());

        return new RepaymentNote(noteHelper.evaluateSurplus(), discRatios,
                noteHelper.evaluateDisc(), noteHelper.evaluateDifficulty());
    }

    /**
     * 标的风险评定
     *
     * @param target 目标标的
     */
    private void setTargetRating(BaseTarget target) {
        // Count number of success target
        long numberOfSuccess = targetRepository.count((Specification<BaseTarget>)
                (root, criteriaQuery, criteriaBuilder) -> {
                    Predicate p = criteriaBuilder.equal(root.get("targetState"), TargetState.IN_THE_PAYMENT);
                    Predicate p2 = criteriaBuilder.equal(root.get("targetState"), TargetState.PAY_OFF);
                    Predicate p3 = criteriaBuilder.equal(root.get("user").get("username"),
                            target.getUser().getUsername());
                    return criteriaBuilder.and(p3, criteriaBuilder.or(p, p2));
                });

        // Begin evaluating
        TargetEvaluator evaluator = new TargetEvaluator(target, numberOfSuccess,
                getUserMonthStatistics(target.getUser().getUsername()));
        double evaluatingResult = evaluator.evaluate();

        target.setTargetRating(TargetRating.of(evaluatingResult));
        target.setTargetRatingScore(evaluatingResult);
    }

    private List<UserMonthStatistics> getUserMonthStatistics(String username) {
        return monthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.ASC, "date"));
    }


    private List<RepaymentRecord> setTargetRepayment(BaseTarget target, BasicTargetRequest request) {
        RepaymentCalculator calculator = RepaymentCalculator.getCalculator(request.getRepaymentType(),
                request.getMoney(), request.getDuration(), request.getInterestRate());

        RepaymentNoteHelper noteHelper =
                new RepaymentNoteHelper(getUserMonthStatistics(target.getUser().getUsername()),
                        request.getDuration(), calculator.getMonthlyRepayment());

        Repayment repayment = new Repayment(target, target.getUser(), request.getInterestRate(),
                calculator.getTotalInterest(), request.getDuration(), request.getStartTime(),
                request.getMoney(), noteHelper.evaluateDifficulty(), request.getRepaymentType());

        target.setRepayment(repayment);

        // Create repayment records
        LocalDate startRepayingTime = request.getStartTime();
        List<RepaymentRecord> records = new ArrayList<>();
        for (int i = 0; i < repayment.getDuration(); i++) {
            RepaymentMonthInfo monthInfo = calculator.monthlyRepayment.get(i);
            RepaymentRecord record = new RepaymentRecord(target.getUser(), target,
                    monthInfo.getSum(), monthInfo.getPrincipal(), i + 1, monthInfo.getInterest(),
                    monthInfo.getRemainingPrincipal(), startRepayingTime.plusMonths(i + 1));
            records.add(record);
        }
        return records;
    }

    private void settingTarget(BaseTarget target, BasicTargetRequest request) {
        List<RepaymentRecord> records = setTargetRepayment(target, request);
        setTargetRating(target);
        targetRepository.save(target);
        repaymentRecordRepository.saveAll(records);
    }

}