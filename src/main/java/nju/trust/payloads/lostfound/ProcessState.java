package nju.trust.payloads.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public enum ProcessState {
    DOING("正在进行"),
    DONE("已完成");

    private String str;

    ProcessState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
