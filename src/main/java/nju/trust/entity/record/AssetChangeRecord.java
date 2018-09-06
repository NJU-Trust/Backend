package nju.trust.entity.record;

import nju.trust.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssetChangeRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    private String reason;

    private Double changeMoney;

    private Double balance;

    public AssetChangeRecord(User user, String reason, Double changeMoney, Double balance) {
        super(user);
        this.reason = reason;
        this.changeMoney = changeMoney;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AssetChangeRecord{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", changeMoney=" + changeMoney +
                ", balance=" + balance +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
