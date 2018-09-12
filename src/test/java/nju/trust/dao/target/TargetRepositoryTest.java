package nju.trust.dao.target;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.target.BaseTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Predicate;

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
        System.out.println(repository.findAll((Specification<BaseTarget>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate result = criteriaBuilder.equal(root.get("user").get("username"), "test");
            return criteriaBuilder.and(result, criteriaBuilder.equal(root.get("useOfFunds"), "衣帽服饰"));
        }));
    }
}