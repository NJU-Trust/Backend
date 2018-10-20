package nju.trust.payloads.target;

import java.time.LocalDate;

/**
 * Author: J.D. Liao
 * Date: 2018/10/20
 * Description:
 */
public class ProjectInformation {

    private LocalDate releaseDate;

    private LocalDate recruitmentDeadline;

    private LocalDate nextDueDate;

    private Long remainingDay;

    private Double repay;

    private Double unrepaidProportion;

    private String purpose;

    private String projectDescription;


    public ProjectInformation(LocalDate releaseDate, LocalDate recruitmentDeadline,
                              LocalDate nextDueDate, Long remainingDay,
                              Double repay, Double unrepaidProportion,
                              String purpose, String projectDescription) {
        this.releaseDate = releaseDate;
        this.recruitmentDeadline = recruitmentDeadline;
        this.nextDueDate = nextDueDate;
        this.remainingDay = remainingDay;
        this.repay = repay;
        this.unrepaidProportion = unrepaidProportion;
        this.purpose = purpose;
        this.projectDescription = projectDescription;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDate getRecruitmentDeadline() {
        return recruitmentDeadline;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public Long getRemainingDay() {
        return remainingDay;
    }

    public Double getRepay() {
        return repay;
    }

    public Double getUnrepaidProportion() {
        return unrepaidProportion;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getProjectDescription() {
        return projectDescription;
    }
}
