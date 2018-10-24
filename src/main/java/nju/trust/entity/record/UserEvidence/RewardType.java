package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 奖励类型
 * @Date: 2018/9/8
 */
public enum RewardType {
    NATION(40),         // 国家
    PROVINCE(30),       // 省
    CITY(20),           // 市
    SCHOOL(10),         // 学校
    INSTITUTE(5),       // 学院
    CERTIFICATE(15);    // 职业证书

    private int score;

    RewardType(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
