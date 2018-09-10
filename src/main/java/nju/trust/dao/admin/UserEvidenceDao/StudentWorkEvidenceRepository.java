package nju.trust.dao.admin.UserEvidenceDao;

import nju.trust.entity.record.UserEvidence.StudentWorkEvidence;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public interface StudentWorkEvidenceRepository extends CrudRepository<StudentWorkEvidence, Long> {
    public List<StudentWorkEvidence> findByItemId(Long id);
}
