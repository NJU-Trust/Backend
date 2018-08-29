package nju.trust.entity;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户审核时，用户及每个条目的审核状态
 * @Date: 2018/8/29
 */
public enum UserCheckState {
    SUBMIT("用户提交审核"),
    PASS("审核通过"),
    REJECT("审核未通过"),
    UPDATE("用户更新审核信息");

    private String str = "";

    UserCheckState(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
