package nju.trust.dao.target;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.SmallProjectClassification;
import nju.trust.entity.target.SmallTarget;
import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.RepaymentType;
import nju.trust.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TargetRepositoryTest {

    private UserRepository userRepository;
    private RepaymentRepository repaymentRepository;
    private TargetRepository repository;

    @Autowired
    public void setRepository(TargetRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setRepaymentRepository(RepaymentRepository repaymentRepository) {
        this.repaymentRepository = repaymentRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void test() {
        User user = userRepository.findByUsername("test_user").get();
        BaseTarget target = new SmallTarget(LocalDate.now(), "test",
                12., 30., "no", SmallProjectClassification.CERTIFICATE_TEST,
                IdentityOption.ONE, user,
                "no", "no");
        Repayment repayment = new Repayment();
        repayment.setType(RepaymentType.EQUAL_PRINCIPAL);
        repayment.setDifficulty(10.);
        repayment.setTarget(target);
        target.setRepayment(repayment);
        repository.save(target);
    }
}