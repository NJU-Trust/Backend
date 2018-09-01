package nju.trust.payloads.admin;

import nju.trust.entity.UserCheckState;

/**
 * @Author: 许杨
 * @Description:管理员进行用户审核的
 * @Date: 2018/8/29
 */
public class UserStateList implements Comparable {
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

    @Override
    public int compareTo(Object o) {
        UserStateList userStateList = (UserStateList)o;
        int priority1 = this.checkState.getPriority();
        int priority2 = userStateList.checkState.getPriority();
        if(priority1 < priority2) {
            return -1;
        }else if(priority1 > priority2) {
            return 1;
        }
        // TODO 提交时间

        return 0;
    }
}
