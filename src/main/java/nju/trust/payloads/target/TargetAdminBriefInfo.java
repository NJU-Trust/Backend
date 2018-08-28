package nju.trust.payloads.target;

import java.util.ArrayList;

/**
 * @Author: 许杨
 * @Description: 管理员进行标的管理时查看的简略信息
 * @Date: 2018/8/28
 */
public class TargetAdminBriefInfo {
    private Long id;                                            // 编号
    private String borrower = "";                               // 借款用户
    private ArrayList<String> investors = new ArrayList<>();    // 投资用户

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

    public ArrayList<String> getInvestors() {
        return investors;
    }

    public void setInvestors(ArrayList<String> investors) {
        this.investors = investors;
    }
}
