package nju.trust.dao.record;

import nju.trust.entity.record.RepaymentRecord;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
public interface RepaymentRecordRepository extends JpaRepository<RepaymentRecord, Long> {

    List<RepaymentRecord> findAllByTargetId(Long targetId, Sort sort);

    List<RepaymentRecord> findAllByTargetId(Long targetId);
}
