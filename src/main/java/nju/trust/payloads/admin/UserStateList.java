package nju.trust.payloads.admin;

import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户审核的
 * @Date: 2018/8/29
 */
public class UserStateList implements Comparable{
    private String username = "";
    private CheckState checkState;
    private LocalDateTime time; // 申请时间

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CheckState getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckState checkState) {
        this.checkState = checkState;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public UserStateList() {
    }

    public UserStateList(UserStateList list) {
        this.username = list.getUsername();
        this.checkState = list.getCheckState();
        this.time = list.getTime();
    }

    public UserStateList(UserInfoCheckRecord record) {
        this.username = record.getUser().getUsername();
        this.time = record.getTime();
        this.checkState = record.getCheckState();
    }

    @Override
    public int compareTo(Object o) {
        UserStateList other = (UserStateList)o;

        if(this.getCheckState().equals(CheckState.ONGING) && other.getCheckState().equals(CheckState.UPDATE)) {
            return 1;
        }else if(this.getCheckState().equals(CheckState.UPDATE) && other.getCheckState().equals(CheckState.ONGING)) {
            return -1;
        }

        if(this.getTime().isBefore(other.getTime())) {
            return -1;
        }else if(this.getTime().isAfter(other.getTime())) {
            return 1;
        }

        return 0;
    }
}
