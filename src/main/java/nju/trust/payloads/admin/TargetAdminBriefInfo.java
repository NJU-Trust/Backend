package nju.trust.payloads.admin;

import nju.trust.entity.target.BaseTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员进行标的管理时查看的简略信息
 * @Date: 2018/8/28
 */
public class TargetAdminBriefInfo {
    private Long id;                                            // 编号
    private String borrower = "";                               // 借款用户
    private List<String> investors = new ArrayList<>();    // 投资用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public List<String> getInvestors() {
        return investors;
    }

    public void setInvestors(List<String> investors) {
        this.investors = investors;
    }

    public TargetAdminBriefInfo(TargetAdminBriefInfo info) {
        this.id = info.getId();
        this.borrower = info.getBorrower();
        this.investors = info.getInvestors();
    }

    public TargetAdminBriefInfo(BaseTarget baseTarget, List<String> investors) {
        this.id = baseTarget.getId();
        this.borrower = baseTarget.getName();
        if (investors == null) {
            this.investors = new ArrayList<>();
        }else {
            this.investors = investors;
        }
    }
}
