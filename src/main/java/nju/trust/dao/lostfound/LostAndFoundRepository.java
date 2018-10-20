package nju.trust.dao.lostfound;

import nju.trust.entity.lostfound.LostAndFound;
import nju.trust.entity.user.User;
import nju.trust.payloads.lostfound.MsgProperty;
import nju.trust.payloads.lostfound.ProcessState;
import nju.trust.payloads.lostfound.ThingsType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public interface LostAndFoundRepository extends CrudRepository<LostAndFound, Long> {
    List<LostAndFound> findDistinctByUserUsernameAndPropertyAndState(String username, MsgProperty property, ProcessState state);
}
