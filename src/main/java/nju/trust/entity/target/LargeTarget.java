package nju.trust.entity.target;

import nju.trust.entity.LargeProjectClassification;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LARGE")
public class LargeTarget extends BaseTarget {

    /**
     * 学习项目类别分类
     */
    private LargeProjectClassification classification;

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
