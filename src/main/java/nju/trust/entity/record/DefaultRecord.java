package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
@Entity
public class DefaultRecord extends BaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    private LocalDate defaultDate;

    private String defaultDetails;

    private DefaultState state;

    public DefaultRecord(User user, BaseTarget target, LocalDate defaultDate,
                         String defaultDetails, DefaultState state) {
        super(user);
        this.target = target;
        this.defaultDate = defaultDate;
        this.defaultDetails = defaultDetails;
        this.state = state;
    }

    public DefaultRecord() {}


    public Long getId() {
        return id;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public LocalDate getDefaultDate() {
        return defaultDate;
    }

    public String getDefaultDetails() {
        return defaultDetails;
    }

    public DefaultState getState() {
        return state;
    }
}
