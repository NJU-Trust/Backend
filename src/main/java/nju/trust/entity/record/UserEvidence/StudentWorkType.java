package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 学生工作类型
 * @Date: 2018/9/8
 */
public enum StudentWorkType {
    MANAGER(50),    // 管理层
    WORKER(20);     // 干事

    private int score;

    public int getScore() {
        return score;
    }

    StudentWorkType(int score) {
        this.score = score;
    }
}
