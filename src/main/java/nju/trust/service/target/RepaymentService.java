package nju.trust.service.target;

import nju.trust.dao.record.DefaultRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.entity.record.DefaultRecord;
import nju.trust.entity.record.DefaultState;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.Repayment;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.target.Default;
import nju.trust.payloads.target.ProjectInformation;
import nju.trust.payloads.target.RepaymentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Author: J.D. Liao
 * Date: 2018/9/15
 * Description:
 */
@Service
public class RepaymentService {

    private RepaymentRecordRepository repaymentRecordRepository;

    private DefaultRecordRepository defaultRecordRepository;

    private TargetRepository targetRepository;

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
        RepaymentRecord repaymentRecord = repaymentRecordRepository
                .findByReturnDateAndTargetId(repayment.nextDueDate(), targetId)
                .orElseThrow(NoSuchElementException::new);

        return new ProjectInformation(target.getStartTime(),
                repayment.getStartDate(), repayment.nextDueDate(), repayment.nextDue(),
                repaymentRecord.getSum(), repayment.getRemainingAmount() / target.getCollectedMoney(),
                target.getUseOfFunds(), target.getProjectDescription());
    }

    public RepaymentAnalysis getRepaymentAnalysis(Long targetId) {
        BaseTarget target = targetRepository.findById(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        Repayment repayment = target.getRepayment();
        List<RepaymentRecord> records = repaymentRecordRepository.findAllByTargetId(targetId);

        return new RepaymentAnalysis(repayment.getType(), repayment.getDifficulty(), records);
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
    public void setTargetRepository(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }
}
