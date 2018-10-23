package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description: 不一定能用到，因为前端不一定写了收藏标的的入口
 */
@Entity
public class CollectTargetRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    public CollectTargetRecord(BaseTarget target, User user) {
        super(user);
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public BaseTarget getTarget() {
        return target;
    }
}
