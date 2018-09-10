package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 科研竞赛获奖情况、奖学金
 * @Date: 2018/9/8
 */
public enum BonusType {
    NATION(40),     // 国家
    PROVINCE(30),   // 省
    CITY(20),       // 市
    SCHOOL(10),     // 学校
    INSTITUTE(5);   // 学院

    private double score;

    public double getScore() {
        return score;
    }

    BonusType(double score) {
        this.score = score;
    }
}
