package nju.trust.dao.lostfound;

import nju.trust.entity.lostfound.LostAndFound;
import nju.trust.entity.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public interface LostAndFoundRepository extends CrudRepository<LostAndFound, Long> {
}
