package nju.trust.payloads.admin;

import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;

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
    private List<String> investors = new ArrayList<>();         // 投资用户
    private String state;                                       // 状态
    private String classification;                              // 类别

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

    public String getState() {
        return state;
    }

    public void setState(TargetState state) {
        this.state = state.getStr();
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public TargetAdminBriefInfo(TargetAdminBriefInfo info) {
        this.id = info.getId();
        this.borrower = info.getBorrower();
        this.investors = info.getInvestors();
        this.state = info.getState();
    }

    public TargetAdminBriefInfo(BaseTarget baseTarget, List<String> investors, String classification) {
        this.id = baseTarget.getId();
        this.borrower = baseTarget.getName();
        if (investors == null) {
            this.investors = new ArrayList<>();
        }else {
            this.investors = investors;
        }
        this.state = baseTarget.getTargetState().getStr();
        this.classification = classification;
    }
}
