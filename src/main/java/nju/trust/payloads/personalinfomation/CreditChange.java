package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 信用变化情况
 * @Date: 2018/9/16
 */
public enum CreditChange {
    BETTER("信用上升"), // （上上月信用-上月信用）/上上月信用<-5%
    WORSE("信用下降"),  // （上上月信用-上月信用）/上上月信用>5%
    NO_CHANGE("信用未改变"),
    WARNING("信用严重下降，警告"),   // 网站中最差的5%
    FROZEN("账户已冻结");    // 失信人

    private final String str;

    CreditChange(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
