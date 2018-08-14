package nju.trust.entity.user;

import nju.trust.entity.CheckState;
import nju.trust.entity.UserLevel;

import javax.persistence.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/9
 */
@Entity
public class CreditCheck {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserLevel currentLevel;

    @Enumerated(EnumType.STRING)
    private UserLevel appliedLevel;

    @Enumerated(EnumType.STRING)
    private CheckState checkState;

    @Override
    public String toString() {
        return "CreditCheck{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", currentLevel=" + currentLevel +
                ", appliedLevel=" + appliedLevel +
                ", checkState=" + checkState +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(UserLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public UserLevel getAppliedLevel() {
        return appliedLevel;
    }

    public void setAppliedLevel(UserLevel appliedLevel) {
        this.appliedLevel = appliedLevel;
    }

    public CheckState getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckState checkState) {
        this.checkState = checkState;
    }
}
