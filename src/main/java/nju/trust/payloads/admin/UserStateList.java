package nju.trust.payloads.admin;

import nju.trust.entity.UserCheckState;

/**
 * @Author: 许杨
 * @Description:管理员进行用户审核的
 * @Date: 2018/8/29
 */
public class UserStateList {
    private String username = "";
    private UserCheckState checkState;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCheckState getCheckState() {
        return checkState;
    }

    public void setCheckState(UserCheckState checkState) {
        this.checkState = checkState;
    }
}
