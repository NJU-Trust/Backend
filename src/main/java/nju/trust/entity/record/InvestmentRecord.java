package nju.trust.entity.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InvestmentRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Long targetId;

    private Double investedMoney;

    public InvestmentRecord(Long targetId, Double investedMoney, String username) {
        super(username);
        this.targetId = targetId;
        this.investedMoney = investedMoney;
    }

    @Override
    public String toString() {
        return "InvestmentRecord{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", investedMoney=" + investedMoney +
                '}';
    }

    public Double getInvestedMoney() {
        return investedMoney;
    }

    public void setInvestedMoney(Double investedMoney) {
        this.investedMoney = investedMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
