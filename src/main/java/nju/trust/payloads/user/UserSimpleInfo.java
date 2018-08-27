package nju.trust.payloads.user;

/**
 * @Author: 161250127
 * @Description: 用于管理员查看列表信息，只有四项内容所以写了这个类
 * @Date: 2018/8/26
 */
public class UserSimpleInfo {
    String username;
    String level;
    String tel;
    String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
}
