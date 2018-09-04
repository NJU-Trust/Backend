package nju.trust.payloads.admin;

import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.UserInfoCheckRecord;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用户信息审核的条目
 * @Date: 2018/9/1
 */
public class UserCheckItem {
    private Long id;    // 编号
    private CheckItem item;         // 条目
    private List<String> evidences; // 证明
    private CheckState checkState;  // 条目状态
    private String description;

    public UserCheckItem(UserInfoCheckRecord record, List<String> evidences) {
        this.id = record.getId();
        this.item = record.getCheckItem();
        this.evidences = evidences;
        this.checkState = record.getCheckState();
        this.description = record.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CheckItem getItem() {
        return item;
    }

    public void setItem(CheckItem item) {
        this.item = item;
    }

    public List<String> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<String> evidences) {
        this.evidences = evidences;
    }

    public CheckState getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckState checkState) {
        this.checkState = checkState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
