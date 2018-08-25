package nju.trust.entity.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RepaymentRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Double money;

    private Long targetId;

    public RepaymentRecord(String username, Double money, Long targetId) {
        super(username);
        this.money = money;
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "RepaymentRecord{" +
                "id=" + id +
                ", money=" + money +
                ", targetId=" + targetId +
                ", username='" + username + '\'' +
                ", time=" + time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
