package nju.trust.payloads.admin;

import nju.trust.entity.UserType;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/1
 */
public class UserListRequest {

    private String key;

    private UserType type;

    public String getKeyword() {
        return key;
    }

    public void setKeyword(String keyword) {
        this.key = keyword;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
