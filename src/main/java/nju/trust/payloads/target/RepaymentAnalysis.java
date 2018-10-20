package nju.trust.payloads.target;

import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.user.RepaymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/10/20
 * Description:
 */
public class RepaymentAnalysis {

    private RepaymentType repaymentType;

    private Double difficulty;

    private List<Action> timeline;

    public RepaymentAnalysis(RepaymentType repaymentType, Double difficulty, List<RepaymentRecord> records) {
        this.repaymentType = repaymentType;
        this.difficulty = difficulty;

        timeline = new ArrayList<>();
        for (RepaymentRecord r : records) {
            String state = r.isOverdue() ? "overdue" : "normal";
            timeline.add(new Action(state, r.getReturnDate(), r.getPeriod(), r.getSum(), r.getTarget().getId()));
        }
    }

    public RepaymentType getRepaymentType() {
        return repaymentType;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public List<Action> getTimeline() {
        return timeline;
    }

    private static class Action {
        private String state;

        private LocalDate date;

        private Integer period;

        private Double money;

        private Long targetId;

        public Action(String state, LocalDate date, Integer period, Double money, Long targetId) {
            this.state = state;
            this.date = date;
            this.period = period;
            this.money = money;
            this.targetId = targetId;
        }
    }
}
