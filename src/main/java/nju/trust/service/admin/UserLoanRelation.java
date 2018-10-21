package nju.trust.service.admin;

/**
 * @Author: 许杨
 * @Description: 统计用户的借款总额
 * @Date: 2018/10/21
 */
public class UserLoanRelation implements Comparable<UserLoanRelation>{
    private String username;
    private double money;

    public UserLoanRelation() {
        username = "";
        money = 0;
    }
    public UserLoanRelation(String username) {
        this.username = username;
        money = 0;
    }
    public UserLoanRelation(String username, double money) {
        this.username = username;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public int compareTo(UserLoanRelation o) {
        if(username.equals(o.username)) {
            return 0;
        }
        if(money > o.money) {
            return -1;
        }else if(money < o.money) {
            return 1;
        }else {
            return username.compareTo(o.username);
        }
    }

    public void plus(UserLoanRelation relation) {
        if(this.compareTo(relation) == 0) {
            money = money + relation.money;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass() == this.getClass()) {
            UserLoanRelation relation = (UserLoanRelation)obj;
            return username.equals(relation.username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        return result;
    }
}
