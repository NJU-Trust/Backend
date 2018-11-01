package nju.trust.service.target;

import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.user.Repayment;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.investment.CompletedTarget;
import nju.trust.payloads.investment.InvestmentTarget;
import nju.trust.payloads.target.DefaultRecord;
import nju.trust.payloads.target.OnGoingTarget;
import nju.trust.payloads.target.ReleasedTarget;
import nju.trust.payloads.target.TargetFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
@Service
public class TargetManagementService {

    private static Logger logger = LoggerFactory.getLogger("TargetManagementService");

    private TargetRepository targetRepository;

    private RepaymentRecordRepository repaymentRecordRepository;

    private InvestmentRecordRepository investmentRecordRepository;

    public List<OnGoingTarget> getOnGoingTargetList(String username, TargetFilter filter) {
        System.out.println(username);
        System.out.println(targetRepository.findAllByUserUsername(username));

        List<BaseTarget> targets = targetRepository.findAllByUserUsername(username)
                .stream()
                .filter(t -> filter.toPredicate().test(t)
                        && t.getTargetState() == TargetState.ON_GOING)
                .collect(Collectors.toList());

        logger.info(targets.toString());

        List<OnGoingTarget> result = new ArrayList<>();
        for (BaseTarget target : targets) {
            Repayment repayment = target.getRepayment();
            RepaymentRecord record = repaymentRecordRepository
                    .findAllByTargetId(target.getId())
                    .stream()
                    .filter(r -> !r.hasPaidOff())
                    .min(Comparator.comparing(RepaymentRecord::getReturnDate))
                    .orElseThrow(IllegalStateException::new);

            int remainingPeriods = repayment.getDuration() - record.getPeriod() + 1;
            result.add(new OnGoingTarget(target.getName(), target.getStartTime(),
                    repayment.getInterestRate(), remainingPeriods, record.getRemainingPrincipal(),
                    repayment.nextDueDate(), record.getSum(), target.getId()));

        }
        return result;
    }

    public List<ReleasedTarget> completedTargetList(String username, TargetFilter filter) {
        return targetRepository.findAllByUserUsername(username).stream()
                .filter(t -> filter.toPredicate().test(t) && t.getTargetState() == TargetState.PAY_OFF)
                .map(ReleasedTarget::new).collect(Collectors.toList());
    }

    public List<ReleasedTarget> releasedTargetList(String username, TargetFilter filter) {
        return targetRepository.findAllByUserUsername(username).stream()
                .filter(t -> filter.toPredicate().test(t))
                .map(ReleasedTarget::new).collect(Collectors.toList());
    }

    public List<InvestmentTarget> investedOngoingTargets(String username, TargetFilter filter) {
        // Apply filter to all ongoing task this user invested
        List<InvestmentRecord> records = investmentRecordRepository.findAllByUserUsername(username)
                .stream()
                .filter(r -> (r.getTarget().getTargetState() == TargetState.ON_GOING
                        || r.getTarget().getTargetState() == TargetState.IN_THE_PAYMENT)
                        && filter.toPredicate().test(r.getTarget()))
                .collect(Collectors.toList());

        List<InvestmentTarget> result = new ArrayList<>();

        for (InvestmentRecord record : records) {
            BaseTarget target = record.getTarget();
            // Find latest repayment record
            Repayment repayment = target.getRepayment();
            RepaymentRecord repaymentRecord = repaymentRecordRepository
                    .findAllByTargetId(target.getId())
                    .stream()
                    .filter(r -> !r.hasPaidOff())
                    .min(Comparator.comparing(RepaymentRecord::getReturnDate))
                    .orElseThrow(NoSuchElementException::new);

            // Add the vo to result list
            result.add(new InvestmentTarget(target.getName(), target.getUser().getUsername(),
                    target.getStartTime(), record.getInvestedMoney(), repayment.getDuration(),
                    repayment.getInterestRate(), repaymentRecord.getSum(), repaymentRecord.getReturnDate(),
                    repayment.getRemainingAmount(), target.getTargetState(), target.getId()));
        }

        return result;
    }

    public List<CompletedTarget> investedCompletedTargets(String username, TargetFilter filter) {
        List<InvestmentRecord> records = investmentRecordRepository.findAllByUserUsername(username)
                .stream()
                .filter(r -> r.getTarget().getTargetState() == TargetState.PAY_OFF
                        && filter.toPredicate().test(r.getTarget()))
                .collect(Collectors.toList());

        List<CompletedTarget> result = new ArrayList<>();

        for (InvestmentRecord record : records) {
            BaseTarget target = record.getTarget();
            Repayment repayment = target.getRepayment();

            // Calculate the end date of this target
            LocalDate startDate = target.getStartTime();
            LocalDate endDate = startDate.plusMonths(target.getRepaymentDuration());

            // Just ues total interest to represent investor's profit
            result.add(new CompletedTarget(target.getName(), target.getUser().getUsername(),
                    startDate, endDate, record.getInvestedMoney(), repayment.getInterestRate(),
                    target.getTargetState(), repayment.getTotalInterest(), target.getId()));
        }

        return result;
    }

    public List<DefaultRecord> defaultRecords(String username) {
        // TODO: 2018/9/11 等有空再实现吧 orz
        return new ArrayList<>();
    }

    @Autowired
    public void setTargetRepository(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Autowired
    public void setRepaymentRecordRepository(RepaymentRecordRepository repaymentRecordRepository) {
        this.repaymentRecordRepository = repaymentRecordRepository;
    }

    @Autowired
    public void setInvestmentRecordRepository(InvestmentRecordRepository investmentRecordRepository) {
        this.investmentRecordRepository = investmentRecordRepository;
    }
}
