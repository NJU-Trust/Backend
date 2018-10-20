package nju.trust.payloads.target;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/20
 * Description:
 */
public class Default {

    private LocalDate repayDate;

    private LocalDate actualDate;

    private Double currentAmountDue;

    private Long overDueDays;

    private Double overDueFines;

    private String state;

    public Default(LocalDate repayDate, LocalDate actualDate,
                   Double currentAmountDue, Long overDueDays,
                   Double overDueFines, String state) {
        this.repayDate = repayDate;
        this.actualDate = actualDate;
        this.currentAmountDue = currentAmountDue;
        this.overDueDays = overDueDays;
        this.overDueFines = overDueFines;
        this.state = state;
    }

    public LocalDate getRepayDate() {
        return repayDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public Double getCurrentAmountDue() {
        return currentAmountDue;
    }

    public Long getOverDueDays() {
        return overDueDays;
    }

    public Double getOverDueFines() {
        return overDueFines;
    }

    public String getState() {
        return state;
    }
}
