package nju.trust.entity.target;

import javax.persistence.Embeddable;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
@Embeddable
public class FeeExplanation {

    private String type;

    private Double money;

    @Override
    public String toString() {
        return "FeeExplanation{" +
                "type='" + type + '\'' +
                ", money=" + money +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

}
