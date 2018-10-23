package nju.trust.service.target;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/10/23
 * Description:
 */
public class ConsumptionAnalysis {

    private Double nextPayment;

    private Long nextPayTime;

    private List<PayItem> nextPayList;

    private UserDataItem user;

    public ConsumptionAnalysis(Double nextPayment, Long nextPayTime,
                               List<PayItem> nextPayList, UserDataItem user) {
        this.nextPayment = nextPayment;
        this.nextPayTime = nextPayTime;
        this.nextPayList = nextPayList;
        this.user = user;
    }

    public Double getNextPayment() {
        return nextPayment;
    }

    public Long getNextPayTime() {
        return nextPayTime;
    }

    public List<PayItem> getNextPayList() {
        return nextPayList;
    }

    public UserDataItem getUser() {
        return user;
    }
}
