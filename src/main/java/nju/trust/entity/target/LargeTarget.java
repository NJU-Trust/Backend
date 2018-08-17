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

    /**
     * Official document and other description files
     */
    @ElementCollection(targetClass = String.class)
    private List<String> files;

    private String projectDescription;

    @Override
    public String toString() {
        return "LargeTarget{" +
                "classification=" + classification +
                ", files=" + files +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LargeProjectClassification getClassification() {
        return classification;
    }

    public void setClassification(LargeProjectClassification classification) {
        this.classification = classification;
    }
}
