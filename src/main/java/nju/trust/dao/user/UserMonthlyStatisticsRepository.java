package nju.trust.dao.user;

import nju.trust.entity.user.UserMonthStatistics;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/30
 * Description:
 */
public interface UserMonthlyStatisticsRepository extends PagingAndSortingRepository<UserMonthStatistics, Long> {

    List<UserMonthStatistics> findAllByUserUsername(String username, Sort sort);

    List<UserMonthStatistics> findAllByUserUsername(String username);
}
