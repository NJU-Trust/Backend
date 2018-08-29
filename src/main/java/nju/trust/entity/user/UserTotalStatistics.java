package nju.trust.entity.user;

import javax.persistence.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/28
 * Description:
 */
@Entity
public class UserTotalStatistics {

    @Id
    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    private Double income;

    private Double expense;

    private Double surplus;

    private Double rigid;

    private Double disc;

    private Double daily;

    private Double debt;

    private Double investment;

    private Double travel;

    private Double fun;

    private Double food;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    public Double getRigid() {
        return rigid;
    }

    public void setRigid(Double rigid) {
        this.rigid = rigid;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getDaily() {
        return daily;
    }

    public void setDaily(Double daily) {
        this.daily = daily;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Double getTravel() {
        return travel;
    }

    public void setTravel(Double travel) {
        this.travel = travel;
    }

    public Double getFun() {
        return fun;
    }

    public void setFun(Double fun) {
        this.fun = fun;
    }

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
