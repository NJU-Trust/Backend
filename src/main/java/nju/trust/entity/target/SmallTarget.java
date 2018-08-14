package nju.trust.entity.target;

import nju.trust.entity.UseClassification;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("SMALL")
public class SmallTarget extends BaseTarget {

    @ElementCollection(targetClass = UseClassification.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UseClassification> purpose;

    @ElementCollection(targetClass = String.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> purposeFiles;

    private String purposeExplanation;

    @Override
    public String toString() {
        return "SmallTarget{" +
                "purpose=" + purpose +
                ", purposeFiles=" + purposeFiles +
                ", purposeExplanation='" + purposeExplanation + '\'' +
                '}' + super.toString();
    }

    public List<UseClassification> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<UseClassification> purpose) {
        this.purpose = purpose;
    }

    public List<String> getPurposeFiles() {
        return purposeFiles;
    }

    public void setPurposeFiles(List<String> purposeFiles) {
        this.purposeFiles = purposeFiles;
    }

    public String getPurposeExplanation() {
        return purposeExplanation;
    }

    public void setPurposeExplanation(String purposeExplanation) {
        this.purposeExplanation = purposeExplanation;
    }
}