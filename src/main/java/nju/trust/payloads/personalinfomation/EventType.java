package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 消息箱事件种类
 * @Date: 2018/9/15
 */
public enum EventType {
    HAVEPAY("待还款"),
    HAVEGOT("已收款");

    private String str;

    EventType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
