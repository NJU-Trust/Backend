package nju.trust.entity.record;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;

import javax.persistence.*;

@Entity
public class InvestmentRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private BaseTarget target;

    private Double investedMoney;

    public InvestmentRecord(User user, BaseTarget target, Double investedMoney) {
        super(user);
        this.target = target;
        this.investedMoney = investedMoney;
    }

    @Override
    public String toString() {
        return "InvestmentRecord{" +
                "id=" + id +
                ", investedMoney=" + investedMoney +
                '}';
    }

    public BaseTarget getTarget() {
        return target;
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

}
