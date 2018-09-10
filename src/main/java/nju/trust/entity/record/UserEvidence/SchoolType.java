package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 学校分类
 * @Date: 2018/9/8
 */
public enum  SchoolType {
    NINEEIGHTFIVE(100),  // 985
    TWOONEONE(60),      // 211
    OTHER(10);           // 其他

    private double score;

    SchoolType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
