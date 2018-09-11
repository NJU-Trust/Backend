package nju.trust.service.target;

import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.Repayment;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.target.DefaultRecord;
import nju.trust.payloads.target.OnGoingTarget;
import nju.trust.payloads.target.ReleasedTarget;
import nju.trust.payloads.target.TargetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
@Service
public class TargetManagementService {

    private TargetRepository targetRepository;

    private RepaymentRecordRepository repaymentRecordRepository;

    public List<OnGoingTarget> getOnGoingTargetList(String username, TargetFilter filter) {
        List<BaseTarget> targets = targetRepository.findAll(filter.toSpecification(username));
        List<OnGoingTarget> result = new ArrayList<>();
        for (BaseTarget target : targets) {
            Repayment repayment = target.getRepayment();
            RepaymentRecord record = repaymentRecordRepository
                    .findByReturnDateAndTargetId(repayment.nextDueDate(), target.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Repayment record not found"));

            int remainingPeriods = repayment.getDuration() - record.getPeriod() + 1;
            result.add(new OnGoingTarget(target.getName(), target.getStartTime(),
                    repayment.getInterestRate(), remainingPeriods, record.getRemainingPrincipal(),
                    repayment.nextDueDate(), record.getSum()));

        }
        return result;
    }

    public List<ReleasedTarget> completedTargetList(String username, TargetFilter filter) {
        return null;
    }

    public List<ReleasedTarget> releasedTargetList(String username, TargetFilter filter) {
        return null;
    }

    public List<DefaultRecord> defaultRecords(String username) {
        return null;
    }

    @Autowired
    public void setTargetRepository(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Autowired
    public void setRepaymentRecordRepository(RepaymentRecordRepository repaymentRecordRepository) {
        this.repaymentRecordRepository = repaymentRecordRepository;
    }
}