package nju.trust.entity.target;

import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.user.User;
import nju.trust.payloads.target.LargeTargetRequest;

import javax.persistence.*;

@Entity
@DiscriminatorValue("LARGE")
public class LargeTarget extends BaseTarget {

    /**
     * 学习项目类别分类
     */
    private LargeProjectClassification classification;

    public LargeTarget(LargeTargetRequest request, User user) {
        super(request, user);
        classification = request.getClassification();

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
