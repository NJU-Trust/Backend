package nju.trust.dao.record;

import nju.trust.entity.record.DefaultRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public interface DefaultRecordRepository extends JpaRepository<DefaultRecord, Long> {

    List<DefaultRecord> findAllByUserUsername(String username);
}
