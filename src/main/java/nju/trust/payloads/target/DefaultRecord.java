package nju.trust.payloads.target;

import nju.trust.entity.record.DefaultState;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/9/11
 * Description:
 */
public class DefaultRecord {

    private String name;

    private Double serviceCharge;

    private Double money;

    private LocalDate defaultDate;

    private Integer defaultDays;

    private String defaultDetails;

    private DefaultState state;

    private Long targetId;


    public DefaultRecord(String name, Double serviceCharge, Double money,
                         LocalDate defaultDate, Integer defaultDays,
                         String defaultDetails, DefaultState state, Long targetId) {
        this.name = name;
        this.serviceCharge = serviceCharge;
        this.money = money;
        this.defaultDate = defaultDate;
        this.defaultDays = defaultDays;
        this.defaultDetails = defaultDetails;
        this.state = state;
        this.targetId = targetId;
    }

    public String getName() {
        return name;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public Double getMoney() {
        return money;
    }

    public LocalDate getDefaultDate() {
        return defaultDate;
    }

    public Integer getDefaultDays() {
        return defaultDays;
    }

    public String getDefaultDetails() {
        return defaultDetails;
    }

    public DefaultState getState() {
        return state;
    }

    public Long getTargetId() {
        return targetId;
    }
}
