package nju.trust.entity.user;

import javax.persistence.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/26
 * Description:
 */
@Entity
public class AssetStatistics {

    @Id
    private String username;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    /**
     * 月收入
     */
    @Column(name = "income_m")
    private Double incomeMonth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getIncomeMonth() {
        return incomeMonth;
    }

    public void setIncomeMonth(Double incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public int getMonthIncomeLevel() {
        double[] slices = {1000., 2000., 3000., 4000., 5000., 10000.};
        int i = 1;
        for (double slice : slices) {
            if (incomeMonth < slice)
                return i;
            i++;
        }

        return i;
    }
}
