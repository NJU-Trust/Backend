package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/8
 */
public enum EducationType {
    FRESHMAN(85, "大一"),                 // 大一
    SOPHOMORE(85, "大二"),                // 大二
    JUNIOR(90, "大三"),                   // 大三
    SEMIOR(95, "大四或大五"),                // 大四及大五
    UNDERGRADUATIONDELAY(75, "本科延迟毕业"), // 本科延迟毕业
    POSTGRADUATE(100, "研究生"),           // 研究生
    POSTGRADUATEDELAY(80, "研究生延迟毕业"),// 研究生延迟毕业
    DOCTOR(100, "博士生");                 // 博士生

    private final double score;
    private final String str;

    EducationType(double score, String str) {
        this.score = score;
        this.str = str;
    }

    public double getScore() {
        return score;
    }

    public String getStr() {
        return str;
    }
}
