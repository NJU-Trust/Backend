package nju.trust.entity.target;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LARGE")
public class LargeTarget extends BaseTarget {

    /**
     * Official document and other description files
     */
    @ElementCollection(targetClass = String.class)
    private List<String> files;

    /**
     * Explanation of each fee
     */
    @ElementCollection(targetClass = FeeExplanation.class)
    private List<FeeExplanation> feeExplanations;

    private String repayPlan;

    private String repayApproach;

    private String projectDescription;

    @Embeddable
    public static class FeeExplanation {

        private String type;

        private Double money;

        @Override
        public String toString() {
            return "FeeExplanation{" +
                    "type='" + type + '\'' +
                    ", money=" + money +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

    }
}
