package nju.trust.service.target;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.record.LoanRecordRepository;
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
import nju.trust.service.admin.AdminService;
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
import java.util.Comparator;
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

    private AdminService adminService;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository,
                             SmallTargetRepository smallTargetRepository,
                             LargeTargetRepository largeTargetRepository,
                             UserRepository userRepository,
                             LoanRecordRepository loanRecordRepository,
                             UserMonthlyStatisticsRepository monthlyStatisticsRepository,
                             InvestmentRecordRepository investmentRecordRepository,
                             RepaymentRepository repaymentRepository,
                             TransferHelper transferHelper,
                             RepaymentRecordRepository repaymentRecordRepository,
                             AdminService adminService) {
        this.targetRepository = targetRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userRepository = userRepository;
        this.monthlyStatisticsRepository = monthlyStatisticsRepository;
        this.investmentRecordRepository = investmentRecordRepository;
        this.repaymentRepository = repaymentRepository;
        this.repaymentRecordRepository = repaymentRecordRepository;
        this.transferHelper = transferHelper;
        this.loanRecordRepository = loanRecordRepository;
        this.adminService = adminService;
    }

    @Override
    public List<InvestmentHistory> getInvestmentHistory(Long targetId) {
        List<InvestmentRecord> records = investmentRecordRepository.findAllByTargetId(targetId);
        return records.stream()
                .map(r -> new InvestmentHistory(r.getTime(), r.getUser().getUsername(), r.getInvestedMoney()))
                .collect(Collectors.toList());
    }

    @Override
    public TargetDetails getTargetDetails(Long targetId) {
        BaseTarget target = targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        Repayment repayment = target.getRepayment();
        log.info(repayment.nextDueDate().toString());
        RepaymentRecord record = repaymentRecordRepository
                .findAllByTargetId(targetId)
                .stream().filter(r -> !r.hasPaidOff())
                .min(Comparator.comparing(RepaymentRecord::getReturnDate))
                .orElseThrow(() -> new ResourceNotFoundException("Repayment records not found"));

        return new TargetDetails(target, record);
    }

    @Override
    public TargetInfo getTargetInfo(Long targetId) {
        return targetRepository.findById(targetId).map(TargetInfo::new)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
    }

    @Override
    public ApiResponse applySmallTarget(SmallTargetRequest request, String username) {
        User user = getUser(username);

        SmallTarget smallTarget = new SmallTarget(LocalDate.now(), request.getName(),
                request.getMoney(), request.getCompletionRate(), request.getProjectDescription(),
                request.getClassification(), request.getIdentityOption(), user,
                request.getUseOfFunds(), request.getProof());

        setAndSaveTarget(smallTarget, request);
        return ApiResponse.successResponse();
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        User user = getUser(username);

        LargeTarget largeTarget = new LargeTarget(LocalDate.now(), request.getName(),
                request.getMoney(), request.getUseOfFunds(), request.getCompletionRate(),
                request.getProjectDescription(), request.getClassification(), user, request.getProof());

        setAndSaveTarget(largeTarget, request);
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
    public List<TargetInfo> filterLargeTargets(LargeTargetFilterRequest filterRequest) {
        Specification<LargeTarget> specification = new LargeTargetSpecification(filterRequest);
        Pageable pageable = PageRequest.of(filterRequest.getPage(), filterRequest.getSize(),new Sort(Sort.Direction.ASC, filterRequest.getProperties()));
        Page<LargeTarget> targets = largeTargetRepository.findAll(specification, pageable);
        return targets.stream().map(LargeTargetInfo::new).collect(Collectors.toList());
    }

    @Override
    public List<TargetInfo> filterSmallTargets(SmallTargetFilterRequest filterRequest) {
        Specification<SmallTarget> specification = new SmallTargetSpecification(filterRequest);
        Pageable pageable = PageRequest.of(filterRequest.getPage(), filterRequest.getSize(),new Sort(Sort.Direction.ASC, filterRequest.getProperties()));
        Page<SmallTarget> targets = smallTargetRepository.findAll(specification, pageable);
        System.out.println(pageable);
        return targets.stream().map(SmallTargetInfo::new).collect(Collectors.toList());
    }

    @Override
    public List<TargetInfo> recommendSmallTargets(SmallTargetFilterRequest filterRequest) {
        // Get top 8 targets with highest success rate
        Specification<SmallTarget> specification = new SmallTargetSpecification(filterRequest);
        List<SmallTarget> targets = smallTargetRepository
                .findAll(specification, Sort.by(Sort.Direction.DESC, "targetRatingScore"))
                .stream().filter(t -> t.getTargetState() == TargetState.ON_GOING)
                .sorted(Comparator.comparing(BaseTarget::getTargetRatingScore).reversed())
                .limit(RECOMMENDATION_NUMBER)
                .collect(Collectors.toList());

        return targets.stream().map(SmallTargetInfo::new).collect(Collectors.toList());
    }

    @Override
    public List<InvestmentStrategy> recommendStrategy(List<Integer> targetIds, double money, double interestRate) {
        List<SmallTarget> targets = targetIds.stream()
                .map(id -> smallTargetRepository.findById(id.longValue())
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
        if (lower < 0)
            lower = 0;

        return new Range<>(lower, upper);
    }

    @Override
    public Double getRemainingAmount(String username) {
        User user = getUser(username);

        List<Repayment> repayments = repaymentRepository.findAllByUserUsername(username);
        return user.getCreditRating().getBorrowingAmount() -
                repayments.stream().mapToDouble(Repayment::getRemainingAmount).sum();
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

    @Override
    public ApiResponse repay(String username, Long targetId, Integer period) {
        RepaymentRecord record = repaymentRecordRepository.findByTargetIdAndPeriod(targetId, period)
                .orElseThrow(() -> new ResourceNotFoundException("Repayment record not found"));

        double money = 0.;
        double serviceCharge = 0.;
        if (record.hasPaidOff())
            return new ApiResponse(false, "This period has been repaid");
        else if (record.isOverdue()) {
            double principalInterestSum = record.getSum();
            long overdueDays = record.getOverdueDays();
            money = principalInterestSum + FineCalculator.getOverdueFine(principalInterestSum, overdueDays);
            serviceCharge = FineCalculator.getOverdueServiceCharge(principalInterestSum, overdueDays);
            log.info("money is " + money);
            log.info("service charge is " + serviceCharge);
        } else if (record.isBeforeSettlementDay()) {
            List<RepaymentRecord> records = repaymentRecordRepository.findAllByTargetId(targetId);
            double prepaymentFine = FineCalculator.getPrepaymentFine(records, period);
            money = record.getSum() + prepaymentFine;
            log.info("money is " + money);
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

    /**
     * 得到坏账记录
     * @param moneyUpper 损失金额上限 null:上不封顶
     * @param moneyLower 损失金额下限 null:下不封底
     * @param state      还款中 已还款 null
     * null表示该条件无限制
     */
    @Override
    public List<BadTarget> getBadTargets(String username, Double moneyUpper, Double moneyLower, String state) {
        List<RepaymentRecord> badRepayment = new ArrayList<>();

        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findAll();
        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            if(adminService.isViolate(repaymentRecord)) {
                badRepayment.add(repaymentRecord);
            }
        }

        List<BadTarget> badTargets = getBadTargetList(badRepayment, username);

        // 筛选
        if(moneyUpper != null) {
            badTargets = checkMoneyUpper(badTargets, moneyUpper);
        }
        if(moneyLower != null) {
            badTargets = checkMoneyLower(badTargets, moneyLower);
        }
        if(state != null) {
            badTargets = checkState(badTargets, state);
        }

        return badTargets;
    }

    @Override
    public List<TargetInfo> searchTargets(String name) {
        return smallTargetRepository.findByNameLike(name).stream().map(SmallTargetInfo::new).collect(Collectors.toList());
    }

    private List<BadTarget> checkMoneyUpper(List<BadTarget> badTargets, Double moneyUpper) {
        int index = 0;
        while(index < badTargets.size()) {
            BadTarget badTarget = badTargets.get(index);
            if(badTarget.getLossAmount() > moneyUpper) {
                badTargets.remove(index);
            }else {
                index++;
            }
        }
        return badTargets;
    }
    private List<BadTarget> checkMoneyLower(List<BadTarget> badTargets, Double moneyLower) {
        int index = 0;
        while(index < badTargets.size()) {
            BadTarget badTarget = badTargets.get(index);
            if(badTarget.getLossAmount() < moneyLower) {
                badTargets.remove(index);
            }else {
                index++;
            }
        }
        return badTargets;
    }
    private List<BadTarget> checkState(List<BadTarget> badTargets, String state) {
        int index = 0;
        while(index < badTargets.size()) {
            BadTarget badTarget = badTargets.get(index);
            if(!badTarget.getState().equals(state)) {
                badTargets.remove(index);
            }else {
                index++;
            }
        }
        return badTargets;
    }
    private List<BadTarget> getBadTargetList(List<RepaymentRecord> badRepayment, String username) {
        List<BadTarget> targets = new ArrayList<>();

        for(RepaymentRecord repaymentRecord : badRepayment) {
            BadTarget badTarget = getBadTarget(repaymentRecord, username);
            targets.add(badTarget);
        }
        return targets;
    }
    private BadTarget getBadTarget(RepaymentRecord repaymentRecord, String username) {
        BadTarget badTarget = new BadTarget();

        BaseTarget baseTarget = repaymentRecord.getTarget();
        badTarget.setTargetId(baseTarget.getId());
        badTarget.setProjectName(baseTarget.getName());
        badTarget.setLoanFrom(baseTarget.getUser().getUsername());
        badTarget.setBadStartDate(repaymentRecord.getReturnDate().toString());
        badTarget.setState(baseTarget.getTargetState().getStr());

        List<InvestmentRecord> investmentRecords = investmentRecordRepository.findDistinctByUserUsernameAndTarget(username, baseTarget);
        double investAmount = 0;
        if(investmentRecords.size() > 0) {
            investAmount = investmentRecords.get(0).getInvestedMoney();
        }
        badTarget.setInvestAmount(investAmount);

        double total = baseTarget.getCollectedMoney();
        double shouldReturn = repaymentRecord.getSum();
        double lossAmount = investAmount*shouldReturn/total;
        badTarget.setLossAmount(lossAmount);

        return badTarget;
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

    private double evaluateTargetRating(BaseTarget target) {
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
        return evaluator.evaluate();
    }

    private List<UserMonthStatistics> getUserMonthStatistics(String username) {
        return monthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.ASC, "date"));
    }

    private Repayment generateRepayment(BaseTarget target, BasicTargetRequest request) {
        RepaymentCalculator calculator = RepaymentCalculator.getCalculator(request.getRepaymentType(),
                request.getMoney(), request.getDuration(), request.getInterestRate());

        RepaymentNoteHelper noteHelper =
                new RepaymentNoteHelper(getUserMonthStatistics(target.getUser().getUsername()),
                        request.getDuration(), calculator.getMonthlyRepayment());

        return new Repayment(target, target.getUser(), request.getInterestRate(),
                calculator.getTotalInterest(), request.getDuration(), request.getStartTime(),
                request.getMoney(), noteHelper.evaluateDifficulty(), request.getRepaymentType());
    }

    private List<RepaymentRecord> getRepaymentRecords(BaseTarget target, BasicTargetRequest request, List<RepaymentMonthInfo> monthlyInfo) {
        LocalDate startRepayingTime = request.getStartTime();
        List<RepaymentRecord> records = new ArrayList<>();
        for (int i = 0; i < monthlyInfo.size(); i++) {
            RepaymentMonthInfo monthInfo = monthlyInfo.get(i);
            RepaymentRecord record = new RepaymentRecord(target.getUser(), target,
                    monthInfo.getSum(), monthInfo.getPrincipal(), i + 1, monthInfo.getInterest(),
                    monthInfo.getRemainingPrincipal(), startRepayingTime.plusMonths(i + 1));
            records.add(record);
        }
        return records;
    }

    private void setAndSaveTarget(BaseTarget target, BasicTargetRequest request) {
        RepaymentCalculator calculator = RepaymentCalculator.getCalculator(request.getRepaymentType(),
                request.getMoney(), request.getDuration(), request.getInterestRate());

        List<RepaymentRecord> records = getRepaymentRecords(target, request, calculator.monthlyRepayment);
        Repayment repayment = generateRepayment(target, request);

        // 下面这段代码具有极强的耦合性，改变顺序会导致程序崩溃，这个是
        // 最糟糕的设计了，违背了构造课上讲的面向接口编程，但是为了节约
        // 时间就暂时不改了 orz
        target.setRepayment(repayment);

        double targetRatingScore = evaluateTargetRating(target);
        target.setTargetRating(TargetRating.of(targetRatingScore));
        target.setTargetRatingScore(targetRatingScore);

        targetRepository.save(target);
        repaymentRecordRepository.saveAll(records);
        loanRecordRepository.save(new LoanRecord(target.getUser(), target));
    }

    private BaseTarget getTarget(Long targetId) {
        return targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}