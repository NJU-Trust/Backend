package nju.trust.dao.admin;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/8/31
 */
public interface BaseTargetRepository extends CrudRepository<BaseTarget, Long> {
    /**
     * 通过标的状态查看记录
     * @param targetState 标的状态
     * @return 记录
     */
    List<BaseTarget> findDistinctByTargetState(TargetState targetState);

    /**
     * 通过标的的状态、类型查看
     * @param targetState 状态
     * @param targetType 类型
     * @return 记录
     */
    List<BaseTarget> findDistinctByTargetStateAndTargetType(TargetState targetState, TargetType targetType);

    List<BaseTarget> findDistinctByUserUsername(String username);

    List<BaseTarget> findDistinctByStartTime(LocalDate date);
}
