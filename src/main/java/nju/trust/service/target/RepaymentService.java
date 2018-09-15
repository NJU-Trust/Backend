package nju.trust.service.target;

import nju.trust.dao.record.DefaultRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.entity.record.DefaultRecord;
import nju.trust.entity.record.DefaultState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/15
 * Description:
 */
@Service
public class RepaymentService {

    private RepaymentRecordRepository repaymentRecordRepository;

    private DefaultRecordRepository defaultRecordRepository;

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

    @Autowired
    public void setRepaymentRecordRepository(RepaymentRecordRepository repaymentRecordRepository) {
        this.repaymentRecordRepository = repaymentRecordRepository;
    }

    @Autowired
    public void setDefaultRecordRepository(DefaultRecordRepository defaultRecordRepository) {
        this.defaultRecordRepository = defaultRecordRepository;
    }
}
