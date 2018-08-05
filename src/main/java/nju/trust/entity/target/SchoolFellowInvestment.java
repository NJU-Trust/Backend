package nju.trust.entity.target;

import nju.trust.entity.InterestPlan;

import javax.persistence.Entity;

@Entity
public class SchoolFellowInvestment extends Investment {

    private InterestPlan interestPlan;

    @Override
    public String toString() {
        return "SchoolFellowInvestment{" +
                "interestPlan=" + interestPlan +
                '}' + super.toString();
    }

    public InterestPlan getInterestPlan() {
        return interestPlan;
    }

    public void setInterestPlan(InterestPlan interestPlan) {
        this.interestPlan = interestPlan;
    }
}
