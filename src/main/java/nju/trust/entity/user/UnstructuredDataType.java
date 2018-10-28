package nju.trust.entity.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/28
 * Description:
 */
public enum UnstructuredDataType {
    SCHOOL(0, 0.0475),             // 学校

    MAJOR(0, 0),

    EDUCATION(0, 0.0455),          // 学历

    ECONOMIC(0, 0),

    GRADE(0, 0.0917),              // 成绩

    FAILED_SUBJECTS(0,0.1839),    // 挂科

    SCHOLARSHIP(0, 0),

    AWARD(0, 0.2866),              // 获奖情况

    COMPETITION_AND_RESEARCH(0, 0),

    VIOLATION(100, 0.0387),          // 违约情况

    TUITION(0, 0),

    BORROWING_BOOK(0, 0),

    CHEATING(0, 0),

    REPAYMENT_SITUATION(0, 0),

    UNTRUSTWORTHY_PEOPLE(0, 0),

    STUDENT_WORK(0, 0),

    VOLENTEER_TIME(0, 0),

    SOCIALITY(0, 0.0334)           // 社交情况
    ;

    private double initialScore;

    private double ratio;   // 计算校园表现时的系数

    UnstructuredDataType(double initialScore, double ratio) {
        this.initialScore = initialScore;
        this.ratio = ratio;
    }

    public double getInitialScore() {
        return initialScore;
    }

    public double getRatio() {
        return ratio;
    }
}
