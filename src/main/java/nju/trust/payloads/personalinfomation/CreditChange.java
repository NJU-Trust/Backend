package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 信用变化情况
 * @Date: 2018/9/16
 */
public enum CreditChange {
    BETTER("信用上升"),
    WORSE("信用下降"),
    NO_CHANGE("信用未改变"),
    WARNING("信用严重下降，警告"),
    FROZEN("账户已冻结");

    private final String str;

    CreditChange(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static CreditChange getCreditChange() {
        double num = Math.random();
        if(num < 0.7) {
            return NO_CHANGE;
        }else if(num < 0.87) {
            return BETTER;
        }else if(num < 0.99) {
            return WORSE;
        }else {
            return WARNING;
        }
    }
}
