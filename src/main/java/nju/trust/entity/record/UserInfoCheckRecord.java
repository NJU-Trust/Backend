package nju.trust.entity.record;

import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户审核的记录
 * @Date: 2018/8/31
 */
@Entity
public class UserInfoCheckRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 编号

    @Enumerated(EnumType.STRING)
    private CheckItem checkItem;    // 条目

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;  // 用户

    @Enumerated(EnumType.STRING)
    private CheckState checkState;  // 审核结果(条目状态)

    private LocalDateTime time; // 申请时间

    private String description; // 用户对应条目的描述（如：获奖的奖项名等）

    private String message;    // 管理员的不通过原因

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CheckItem getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
