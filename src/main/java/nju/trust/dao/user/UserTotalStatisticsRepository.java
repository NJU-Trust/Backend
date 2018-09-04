package nju.trust.dao.user;

import nju.trust.entity.user.UserTotalStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: J.D. Liao
 * Date: 2018/9/1
 * Description:
 */
public interface UserTotalStatisticsRepository extends JpaRepository<UserTotalStatistics, Long> {

    UserTotalStatistics findByUserUsername(String username);
}
