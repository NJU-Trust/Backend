package nju.trust.entity.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/28
 * Description:
 */
public enum UnstructuredDataType {
    SCHOOL(0),             // 学校

    MAJOR(0),

    EDUCATION(0),          // 学历

    ECONOMIC(0),

    GRADE(0),              // 成绩

    FAILED_SUBJECTS(0),    // 挂科

    SCHOLARSHIP(0),

    AWARD(0),              // 获奖情况

    COMPETITION_AND_RESEARCH(0),

    VIOLATION(100),          // 违约情况

    TUITION(0),

    BORROWING_BOOK(0),

    CHEATING(0),

    REPAYMENT_SITUATION(0),

    UNTRUSTWORTHY_PEOPLE(0),

    STUDENT_WORK(0),

    VOLENTEER_TIME(0),

    SOCIALITY(0)           // 社交情况
    ;

    private double initialScore;

    UnstructuredDataType(double initialScore) {
        this.initialScore = initialScore;
    }

    public double getInitialScore() {
        return initialScore;
    }
}
