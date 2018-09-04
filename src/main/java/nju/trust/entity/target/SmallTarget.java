package nju.trust.entity.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("SMALL")
public class SmallTarget extends BaseTarget {

    private SmallProjectClassification classification;

    public SmallTarget(LocalDate startTime, String name, Double money, Double completionRate,
                       String projectDescription, SmallProjectClassification classification,
                       IdentityOption identityOption, User user) {
        super(startTime, name, money, completionRate, projectDescription, user);

        this.classification = classification;
        this.identityOption = identityOption;
        targetType = TargetType.SMALL;
    }

    @Override
    public String toString() {
        return "SmallTarget{" +
                "classification=" + classification +
                '}';
    }

    public SmallProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SmallProjectClassification classification) {
        this.classification = classification;
    }

}
