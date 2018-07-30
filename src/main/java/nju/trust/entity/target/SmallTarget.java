package nju.trust.entity.target;

import nju.trust.entity.UserPurpose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.util.List;

@Entity
@Inheritance
@DiscriminatorValue("SMALL")
public class SmallTarget extends BaseTarget {

    @ElementCollection(targetClass = UserPurpose.class)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserPurpose> purpose;

    @ElementCollection(targetClass = String.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> purposeFiles;

    private String purposeExplanation;

    public List<UserPurpose> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<UserPurpose> purpose) {
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

    @Override
    public String toString() {
        return "SmallTarget{" +
                "purpose=" + purpose +
                ", purposeFiles=" + purposeFiles +
                ", purposeExplanation='" + purposeExplanation + '\'' +
                '}' + super.toString();
    }
}
