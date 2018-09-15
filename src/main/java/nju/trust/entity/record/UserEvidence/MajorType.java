package nju.trust.entity.record.UserEvidence;

/**
 * @Author: 许杨
 * @Description: 专业类型
 * @Date: 2018/9/8
 */
public enum MajorType {
    COMPREHENSIVE("综合"),        // 综合
    MULTIDISCIPLINARY("多科"),    // 多科
    SINGLESUBJECT("单科");        // 单科

    private String str;

    MajorType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
