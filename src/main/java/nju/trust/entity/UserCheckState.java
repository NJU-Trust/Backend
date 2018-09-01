package nju.trust.entity;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户审核时，用户及每个条目的审核状态
 * @Date: 2018/8/29
 */
public enum UserCheckState {
    SUBMIT("用户提交审核", 1),
    PASS("审核通过", -1),
    REJECT("审核未通过", -1),
    UPDATE("用户更新审核信息", 0);

    private String str;
    private int priority;

    UserCheckState(String str, int priority) {
        this.str = str;
        this.priority = priority;
    }

    public String getStr() {
        return str;
    }

    public int getPriority() {
        return priority;
    }
}
