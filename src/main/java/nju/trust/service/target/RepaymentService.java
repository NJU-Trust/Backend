package nju.trust.service.target;

import nju.trust.dao.record.DefaultRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.dao.user.UserMonthlyStatisticsRepository;
import nju.trust.dao.user.UserTotalStatisticsRepository;
import nju.trust.entity.record.DefaultRecord;
import nju.trust.entity.record.DefaultState;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.UserMonthStatistics;
import nju.trust.entity.user.UserTotalStatistics;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.target.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/9/15
 * Description:
 */
@Service
public class RepaymentService {

    private static final int PREDICT_MONTHS = 12;

    private RepaymentRecordRepository repaymentRecordRepository;

    private DefaultRecordRepository defaultRecordRepository;

    private TargetRepository targetRepository;

    private UserMonthlyStatisticsRepository userMonthlyStatisticsRepository;

    private UserTotalStatisticsRepository userTotalStatisticsRepository;

    public void checkForDefault() {
        final LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        List<DefaultRecord> defaultRecords = new ArrayList<>();
        repaymentRecordRepository.findAllByReturnDate(yesterday)
                .stream().filter(r -> !r.hasPaidOff())
                .forEach(e -> {
                    DefaultRecord record = new DefaultRecord(e.getUser(), e.getTarget(),
                            today, "", DefaultState.NOT_RETURNED);
                    defaultRecords.add(record);
                });

        defaultRecordRepository.saveAll(defaultRecords);
    }

    public List<Default> getDefaults(String username) {
        List<DefaultRecord> records = defaultRecordRepository.findAllByUserUsername(username);
        List<Default> result = new ArrayList<>();
        for (DefaultRecord record : records) {
            RepaymentRecord repaymentRecord = repaymentRecordRepository
                    .findByReturnDateAndTargetId(record.getDefaultDate(), record.getTarget().getId())
                    .orElseThrow(NoSuchElementException::new);

            long overdueDays = repaymentRecord.getOverdueDays();
            double fine = FineCalculator.getOverdueFine(repaymentRecord.getSum(), overdueDays);

            Default item = new Default(repaymentRecord.getReturnDate(), repaymentRecord.getActualRepayDate(),
                    repaymentRecord.getSum(), overdueDays, fine, record.getState().toString());

            result.add(item);
        }

        return result;
    }

    public ProjectInformation getProjectInformation(Long targetId) {
        BaseTarget target = targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        Repayment repayment = target.getRepayment();
        List<RepaymentRecord> records = repaymentRecordRepository
                .findAllByTargetId(targetId)
                .stream()
                .filter(r -> !r.hasPaidOff()).collect(Collectors.toList());

        RepaymentRecord repaymentRecord = records.stream()
                .min(Comparator.comparing(RepaymentRecord::getReturnDate))
                .orElseThrow(NoSuchElementException::new);

        double remainingMoneyToBePay = records.stream().mapToDouble(RepaymentRecord::getSum).sum();


        return new ProjectInformation(target.getStartTime(),
                repayment.getStartDate(), repayment.nextDueDate(), repayment.nextDue(),
                repaymentRecord.getSum(), repayment.getRemainingAmount() / target.getCollectedMoney(),
                target.getUseOfFunds(), target.getProjectDescription(), target.getTargetType(),
                repayment.getInterestRate(), records.size(), remainingMoneyToBePay, target.getIdentityOption());
    }

    public RepaymentAnalysis getRepaymentAnalysis(Long targetId) {
        BaseTarget target = targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        Repayment repayment = target.getRepayment();
        List<RepaymentRecord> records = repaymentRecordRepository.findAllByTargetId(targetId)
                .stream()
                .sorted(Comparator.comparing(RepaymentRecord::getReturnDate))
                .collect(Collectors.toList());

        return new RepaymentAnalysis(repayment.getType(), repayment.getDifficulty(), records);
    }

    public ConsumptionAnalysis consumptionAnalysis(String username) {
        // Find user information firstly
        UserDataItem userDataItem = getUserDataItem(username);

        List<RepaymentRecord> records = repaymentRecordRepository.findAllByUserUsername(username);

        // find latest repayment record
        RepaymentRecord latestRecord = findLatestRepaymentRecord(records);
        long nextPaymentDay = LocalDate.now().until(latestRecord.getReturnDate(), ChronoUnit.DAYS);

        List<BaseTarget> targets = targetRepository.findAllByUserUsername(username)
                .stream().filter(t -> t.getTargetState() == TargetState.IN_THE_PAYMENT)
                .collect(Collectors.toList());

        List<PayItem> nextPayList = new ArrayList<>();
        for (BaseTarget target : targets) {
            // Generate PayItem for each target
            Optional.ofNullable(getPayItemForTarget(target)).ifPresent(nextPayList::add);
        }

        return new ConsumptionAnalysis(latestRecord.getSum(), nextPaymentDay, nextPayList, userDataItem);
    }

    public SurplusPrediction surplusPrediction(String username) {

        // Find user monthly data
        List<UserMonthStatistics> userMonthStatistics =
                userMonthlyStatisticsRepository.findAllByUserUsername(username,
                        Sort.by(Sort.Direction.ASC, "date"));

        UserMonthlyDataHelper userMonthlyDataHelper = new UserMonthlyDataHelper(userMonthStatistics);

        // Get forecast values of surplus and disc
        List<Double> kn = userMonthlyDataHelper.forecastSurplus(PREDICT_MONTHS);
        List<Double> an = userMonthlyDataHelper.forecastDisc(PREDICT_MONTHS);

        // Generate time line
        List<LocalDate> time = new ArrayList<>();
        for (int i = 0; i < PREDICT_MONTHS; i++) {
            time.add(LocalDate.now().plusMonths(i).withDayOfMonth(1));
        }

        // Generate table data
        ConsumptionCorrection consumptionCorrection = getConsumptionCorrection(userMonthStatistics, username);
        return new SurplusPrediction(time, kn, an, getTableData(consumptionCorrection));
    }

    private List<SurplusPrediction.TableItem> getTableData(ConsumptionCorrection consumptionCorrection) {
        List<SurplusPrediction.TableItem> result = new ArrayList<>();
        List<Double> surplusRatios = consumptionCorrection.getSurplusRatios();
        List<Double> discRatios = consumptionCorrection.getDiscRatios();
        List<Double> extraIncomes = consumptionCorrection.getExtraIncomes();

        assert surplusRatios.size() == PREDICT_MONTHS
                && discRatios.size() == PREDICT_MONTHS
                && extraIncomes.size() == PREDICT_MONTHS;

        for (int i = 0; i < PREDICT_MONTHS; i++) {
            int month = LocalDate.now().plusMonths(i).getMonthValue();
            result.add(new SurplusPrediction.TableItem(month, surplusRatios.get(i),
                    discRatios.get(i), extraIncomes.get(i)));
        }

        return result;
    }

    private ConsumptionCorrection getConsumptionCorrection(List<UserMonthStatistics> monthStatistics, String username) {
        // Calculate monthly repayment
        List<RepaymentRecord> records = repaymentRecordRepository.findAllByUserUsername(username)
                .stream().filter(r -> r.getReturnDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(RepaymentRecord::getReturnDate))
                .collect(Collectors.toList());

        // If no data, return ConsumptionCorrection with empty lists
        List<Double> monthlyRepayment;
        if (records.isEmpty()) {
            monthlyRepayment = Collections.nCopies(PREDICT_MONTHS, 0.);
        } else {
            // Iterate records and merge the records with same month
            monthlyRepayment = new ArrayList<>();
            RepaymentRecord previousRecord = records.get(0);
            double currRepayment = 0;
            for (RepaymentRecord r : records) {
                if (r.getReturnDate().getMonthValue() == previousRecord.getReturnDate().getMonthValue()) {
                    currRepayment += r.getSum();
                } else {
                    monthlyRepayment.add(currRepayment);
                    currRepayment = r.getSum();
                    previousRecord = r;
                }
            }
            monthlyRepayment.add(currRepayment);

            // If not full, add to 12
            if (monthlyRepayment.size() < PREDICT_MONTHS) {
                int need = PREDICT_MONTHS - monthlyRepayment.size();
                for (int i = 0; i < need; i++) {
                    monthlyRepayment.add(0.);
                }
            }
        }

        ConsumptionCorrectionEvaluator evaluator = new ConsumptionCorrectionEvaluator(monthStatistics,
                null, monthlyRepayment);

        return evaluator.getConsumptionCorrection(PREDICT_MONTHS, 0);
    }

    private UserDataItem getUserDataItem(String username) {
        // Find latest month data for this user
        List<UserMonthStatistics> monthStatistics = userMonthlyStatisticsRepository
                .findAllByUserUsername(username, Sort.by(Sort.Direction.DESC, "date"));
        if (monthStatistics.isEmpty())
            return new UserDataItem(0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0);

        UserMonthStatistics latestMonthStatistics = monthStatistics.get(0);

        // Get total statistics
        UserTotalStatistics totalStatistics = userTotalStatisticsRepository.findByUserUsername(username);

        return new UserDataItem(totalStatistics.getSurplus(), totalStatistics.getSurplus(),
                latestMonthStatistics.getEngel(), totalStatistics.getRigid(),
                latestMonthStatistics.getDebtToAssetRatio(), totalStatistics.getDebt(),
                totalStatistics.getDaily(), latestMonthStatistics.getLeverage(),
                latestMonthStatistics.getConsumptionRatio(), latestMonthStatistics.getSavingRatio());
    }

    private RepaymentRecord findLatestRepaymentRecord(List<RepaymentRecord> records) {
        return records.stream()
                .filter(RepaymentRecord::isBeforeSettlementDay)
                .min(Comparator.comparing(RepaymentRecord::remainingDays))
                .orElseThrow(NoSuchElementException::new);
    }

    private PayItem getPayItemForTarget(BaseTarget target) {
        List<RepaymentRecord> records = repaymentRecordRepository
                .findAllByTargetId(target.getId(), Sort.by(Sort.Direction.ASC, "returnDate"))
                .stream().filter(r -> !r.hasPaidOff())
                .collect(Collectors.toList());
        // Find latest three record
        if (records.isEmpty())
            return null;

        List<RepaymentRecord> latestThreeRecords = new ArrayList<>(3);
        for (RepaymentRecord r : records) {
            latestThreeRecords.add(r);
            if (latestThreeRecords.size() == 3)
                break;
        }

        // Initialize pay item
        Repayment repayment = target.getRepayment();
        double interestPlus = repayment.getTotalInterest() + target.getCollectedMoney();
        PayItem payItem = new PayItem(target.getName(), target.getStartTime(), target.getRepaymentDuration(),
                interestPlus);

        // Set latest three period repayment information for pay item
        if (latestThreeRecords.size() >= 1)
            payItem.setA(latestThreeRecords.get(0));

        if (latestThreeRecords.size() >= 2)
            payItem.setB(latestThreeRecords.get(1));

        if (latestThreeRecords.size() == 3)
            payItem.setC(latestThreeRecords.get(2));

        return payItem;
    }


    @Autowired
    public void setRepaymentRecordRepository(RepaymentRecordRepository repaymentRecordRepository) {
        this.repaymentRecordRepository = repaymentRecordRepository;
    }

    @Autowired
    public void setDefaultRecordRepository(DefaultRecordRepository defaultRecordRepository) {
        this.defaultRecordRepository = defaultRecordRepository;
    }

    @Autowired
    public void setUserMonthlyStatisticsRepository(UserMonthlyStatisticsRepository userMonthlyStatisticsRepository) {
        this.userMonthlyStatisticsRepository = userMonthlyStatisticsRepository;
    }

    @Autowired
    public void setUserTotalStatisticsRepository(UserTotalStatisticsRepository userTotalStatisticsRepository) {
        this.userTotalStatisticsRepository = userTotalStatisticsRepository;
    }

    @Autowired
    public void setTargetRepository(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }
}
