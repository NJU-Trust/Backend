package nju.trust.payloads.target;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description:
 */
public class BasicTargetRequest {

    private LocalDateTime startTime;

    private Double money;

    private String name;

    private String projectDescription;

    private MultipartFile[] files;

    private Double completionRate;

    @Override
    public String toString() {
        return "BasicTargetRequest{" +
                "startTime=" + startTime +
                ", money=" + money +
                ", name='" + name + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", files=" + Arrays.toString(files) +
                ", completionRate=" + completionRate +
                '}';
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

}
