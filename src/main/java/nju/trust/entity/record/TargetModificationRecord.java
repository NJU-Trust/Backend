package nju.trust.entity.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TargetModificationRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Double moneyBefore;

    private Double moneyAfter;

    private Long targetId;

    public TargetModificationRecord(String username, Double moneyBefore, Double moneyAfter, Long targetId) {
        super(username);
        this.moneyBefore = moneyBefore;
        this.moneyAfter = moneyAfter;
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "TargetModificationRecord{" +
                "id=" + id +
                ", moneyBefore=" + moneyBefore +
                ", moneyAfter=" + moneyAfter +
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

    public Double getMoneyBefore() {
        return moneyBefore;
    }

    public void setMoneyBefore(Double moneyBefore) {
        this.moneyBefore = moneyBefore;
    }

    public Double getMoneyAfter() {
        return moneyAfter;
    }

    public void setMoneyAfter(Double moneyAfter) {
        this.moneyAfter = moneyAfter;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
