package nju.trust.entity.target;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
@Entity
public class PayBack {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String investor;

    private Double money;

    private LocalDateTime time;

    @Override
    public String toString() {
        return "PayBack{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", investor='" + investor + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
