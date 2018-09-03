package nju.trust.payloads.admin;

import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用户信息审核的条目
 * @Date: 2018/9/1
 */
public class UserCheckItem {
    private CheckItem item;         // 条目
    private List<String> evidences; // 证明
    private CheckState checkState;  // 条目状态

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
}
