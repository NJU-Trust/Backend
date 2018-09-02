package nju.trust.payloads.admin;

import nju.trust.entity.UserType;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/1
 */
public class UserListRequest {

    private String keyword;

    private UserType type;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
