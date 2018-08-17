package nju.trust.payloads.target;

import nju.trust.entity.target.FeeExplanation;
import nju.trust.entity.target.LargeTarget;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
public class LargeTargetInfo extends TargetInfo {

    private List<String> files;

    private String projectDescription;

    public LargeTargetInfo(LargeTarget target) {
        super(target);
        files = target.getFiles();
        projectDescription = target.getProjectDescription();
    }

    @Override
    public String toString() {
        return "LargeTargetInfo{" +
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
}
