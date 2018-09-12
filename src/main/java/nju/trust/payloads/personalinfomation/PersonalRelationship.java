package nju.trust.payloads.personalinfomation;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/12
 */
public class PersonalRelationship {

    private String username;    //用户名（可以直接得到，此处是为了配合前端数据格式）

    private String othersName;    //此条关系对应的人物姓名

    private double creditScore;     //信用得分

    private double financialScore;      //经济得分

    private double campusPerformanceScore;   //校园表现得分

    private String relationship;    //关系名称

    private String creditChange;    //信用变化情况

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOthersName() {
        return othersName;
    }

    public void setOthersName(String othersName) {
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
