package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 个人信息凭证类型
 * @Date: 2018/10/27
 */
public enum SelfInfoEvidenceType {
    STUDENTCARD("学生证"),
    SCHOOLCARD("校园卡"),
    EDUCATION("学历");

    private final String str;

    SelfInfoEvidenceType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
