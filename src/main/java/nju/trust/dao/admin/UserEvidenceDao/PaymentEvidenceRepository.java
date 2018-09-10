package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.PaymentEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface PaymentEvidenceRepository extends CrudRepository<PaymentEvidence, Long> {
    List<PaymentEvidence> findByItemId(Long id);
}
