package nju.trust.dao.record;

import nju.trust.entity.record.LoanRecord;
import nju.trust.exception.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Author: J.D. Liao
 * Date: 2018/9/5
 * Description:
 */
public interface LoanRecordRepository extends CrudRepository<LoanRecord, Long> {
    Optional<LoanRecord> findByTargetId(Long id);

    default void makeLoanRecordSucceed(Long targetId) {
        LoanRecord loanRecord = findByTargetId(targetId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan record not found"));
        loanRecord.makeItSucceed();
        save(loanRecord);
    }
}
