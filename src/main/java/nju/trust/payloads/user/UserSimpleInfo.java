package nju.trust.payloads.user;

import nju.trust.entity.CreditRating;
import nju.trust.entity.UserType;
import nju.trust.entity.user.User;

/**
 * @Author: 161250127
 * @Description: 用于管理员查看列表信息，只有四项内容所以写了这个类
 * @Date: 2018/8/26
 */
public class UserSimpleInfo {
    private String username;
    private CreditRating level;
    private String tel;
    private String email;
    private String state;

    public UserSimpleInfo(UserSimpleInfo userSimpleInfo) {
        this.username = userSimpleInfo.getUsername();
        this.level = userSimpleInfo.getLevel();
        this.tel = userSimpleInfo.getTel();
        this.email = userSimpleInfo.getEmail();
        this.state = userSimpleInfo.getState();
    }
    public UserSimpleInfo(User user, UserType type) {
        this.username = user.getUsername();
        this.level = user.getCreditRating();
        this.email = user.getEmail();
        this.tel = user.getPhoneNumber();
        this.state = type.getStr();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreditRating getLevel() {
        return level;
    }

    public void setLevel(CreditRating level) {
        this.level = level;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserSimpleInfo() { }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setState(UserType type) {
        this.state = type.getStr();
    }
}
