package nju.trust.entity.target;

import nju.trust.entity.SmallProjectClassification;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("SMALL")
public class SmallTarget extends BaseTarget {

    private SmallProjectClassification classification;

    /**
     * 可承受额外费用偏好（希望为这笔融资至多付多少费用）
     */
    private Double maximumAmount;

    @Override
    public String toString() {
        return "SmallTarget{" +
                "classification=" + classification +
                ", maximumAmount=" + maximumAmount +
                '}';
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }

    public Double getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(Double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
}
