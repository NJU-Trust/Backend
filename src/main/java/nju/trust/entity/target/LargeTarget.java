package nju.trust.entity.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LargeTarget extends BaseTarget {

    public LargeTarget() {
    }

    /**
     * 学习项目类别分类
     */
    private LargeProjectClassification classification;

    public LargeTarget(LocalDate startTime, String name, Double money, String useOfFunds,
                       Double completionRate, String projectDescription,
                       LargeProjectClassification classification, User user, String proof) {
        super(startTime, name, money,useOfFunds, completionRate, projectDescription, user, proof);

        this.classification = classification;
        identityOption = IdentityOption.ONE;
        targetType = TargetType.LARGE;
    }

    @Override
    public String toString() {
        return "LargeTarget{" +
                "classification=" + classification +
                '}';
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }
}
