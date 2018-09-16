package nju.trust.payloads.personalinfomation;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/12
 */
public class PersonalRelationship {

    private String username;    //用户名（可以直接得到，此处是为了配合前端数据格式）

    private List<String> othersName;    //此条关系对应的人物姓名

    private double creditScore;     //信用得分

    private double financialScore;      //经济得分

    private double campusPerformanceScore;   //校园表现得分

    private String relationship;    //关系名称

    private String creditChange;    //信用变化情况    // TODO enum 详细解释

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getOthersName() {
        return othersName;
    }

    public void setOthersName(List<String> othersName) {
        this.othersName = othersName;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public double getFinancialScore() {
        return financialScore;
    }

    public void setFinancialScore(double financialScore) {
        this.financialScore = financialScore;
    }

    public double getCampusPerformanceScore() {
        return campusPerformanceScore;
    }

    public void setCampusPerformanceScore(double campusPerformanceScore) {
        this.campusPerformanceScore = campusPerformanceScore;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCreditChange() {
        return creditChange;
    }

    public void setCreditChange(String creditChange) {
        this.creditChange = creditChange;
    }
}
