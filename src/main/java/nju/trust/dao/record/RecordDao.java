package nju.trust.dao.record;

import nju.trust.entity.record.BaseRecord;
import nju.trust.entity.record.LoanRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Author: J.D. Liao
 * Date: 2018/9/4
 * Description:
 */
@Repository("recordDao")
public class RecordDao {

    private EntityManager entityManager;


    public void save(BaseRecord record) {
        entityManager.persist(record);
        entityManager.flush();
    }


    @Autowired
    private void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
