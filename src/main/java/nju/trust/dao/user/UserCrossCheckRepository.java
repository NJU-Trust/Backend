package nju.trust.dao.user;

import nju.trust.entity.user.CreditCrossCheck;
import nju.trust.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/21
 */
public interface UserCrossCheckRepository extends CrudRepository<CreditCrossCheck, Long> {

    List<CreditCrossCheck> findAllByRelatedPersonUsernameAndValid(String username,boolean valid);

    Optional<CreditCrossCheck> findById(long id);

    boolean existsById(long id);

    List<User> findDistinctByEndDate(LocalDate date);

    List<CreditCrossCheck> findAllByUserUsernameAndEndDate(String username, LocalDate date);

}
