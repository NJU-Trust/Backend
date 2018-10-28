package nju.trust.entity.target;

/**
 * Author: J.D. Liao
 * Date: 2018/8/17
 * Description: 学习需求分类
 */
public enum LargeProjectClassification {
    /**
     * 大额考证
     */
    CERTIFICATE_TEST("大额考证"),
    /**
     * 交换生
     */
    EXCHANGE_PROJECT("交换生"),

    GMAT("GMAT"),

    TOEFL("TOEFL"),

    IELTS("IELTS");

    private String str;

    LargeProjectClassification(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
