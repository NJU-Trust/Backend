package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 个人信息审核类别
 * @Date: 2018/10/27
 */
public enum SelfInfoType {
    STUDENT("学生"),
    ALUMNA("校友");

    private final String str;

    SelfInfoType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
