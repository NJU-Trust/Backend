package nju.trust.dao.record;

import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
public interface RepaymentRecordRepository extends JpaRepository<RepaymentRecord, Long> {

    List<RepaymentRecord> findAllByTargetId(Long targetId, Sort sort);

    Optional<RepaymentRecord> findByTargetIdAndPeriod(Long targetId, Integer period);

    List<RepaymentRecord> findAllByTargetId(Long targetId);

    List<RepaymentRecord> findAllByUserUsername(String username);

    Optional<RepaymentRecord> findByReturnDateAndTargetId(LocalDate returnDate, Long targetId);

    List<RepaymentRecord> findAllByReturnDate(LocalDate returnDate);

    List<RepaymentRecord> findDistinctByReturnDateBetween(LocalDate start, LocalDate end);

    List<RepaymentRecord> findDistinctByTargetIdAndReturnDateBetween(Long targetId, LocalDate start, LocalDate end);

    List<RepaymentRecord> findDistinctByReturnDateLessThan(LocalDate date);

    List<RepaymentRecord> findDistinctByUserUsernameAndReturnDateBetween(String username, LocalDate start, LocalDate end);
}
