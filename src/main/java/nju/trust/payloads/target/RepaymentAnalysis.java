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

    private Integer currentPeriod;

    public RepaymentAnalysis(RepaymentType repaymentType, Double difficulty, List<RepaymentRecord> records) {
        this.repaymentType = repaymentType;
        this.difficulty = difficulty;

        timeline = new ArrayList<>();
        for (RepaymentRecord r : records) {
            timeline.add(new Action("time", r.getReturnDate(), r.getPeriod(), r.getSum(), r.getTarget().getId()));
            if (r.isOverdue())
                timeline.add(new Action("overdue", r.getReturnDate(), r.getPeriod(), r.getSum(), r.getTarget().getId()));

            if (r.hasPaidOff())
                timeline.add(new Action("normal", r.getActualRepayDate(), r.getPeriod(), r.getSum(), r.getTarget().getId()));
        }

        currentPeriod = records.stream().filter(r -> !r.hasPaidOff())
                .findFirst().map(RepaymentRecord::getPeriod).orElse(-1);
    }

    public Integer getCurrentPeriod() {
        return currentPeriod;
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

        Action(String state, LocalDate date, Integer period, Double money, Long targetId) {
            this.state = state;
            this.date = date;
            this.period = period;
            this.money = money;
            this.targetId = targetId;
        }

        public String getState() {
            return state;
        }

        public LocalDate getDate() {
            return date;
        }

        public Integer getPeriod() {
            return period;
        }

        public Double getMoney() {
            return money;
        }

        public Long getTargetId() {
            return targetId;
        }
    }
}
