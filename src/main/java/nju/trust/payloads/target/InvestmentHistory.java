package nju.trust.payloads.target;

import java.time.LocalDateTime;

/**
 * Author: J.D. Liao
 * Date: 2018/10/20
 * Description:
 */
public class InvestmentHistory {

    private LocalDateTime date;

    private String name;

    private Double money;

    public InvestmentHistory(LocalDateTime date, String name, Double money) {
        this.date = date;
        this.name = name;
        this.money = money;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return money;
    }
}
