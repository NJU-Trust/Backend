package nju.trust.entity.user;

import nju.trust.entity.SchoolType;

import javax.persistence.Embeddable;
import java.util.Map;

/**
 * Author: J.D. Liao
 * Date: 2018/8/23
 * Description:
 */
@Embeddable
public class BasicInformation {

    private SchoolType schoolType;

    private Double rankingRate;

    private Integer failedSubjects;

    private Map<AwardLevel, Integer> awards;

    private Map<DefaultType, Integer> defaultRecords;

    private StudentWorkType studentWorkType;

    /**
     * 平均每年志愿时长
     */
    private Double avragedVolunteerTime;

    private EducationLevel educationLevel;
}
