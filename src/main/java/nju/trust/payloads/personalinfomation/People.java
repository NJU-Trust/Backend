package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/16
 */
public class People {
    private String name;    // 用户名
    private double creditPts;   // 人物信用分数
    private double financialPts;    // 经济分数
    private double schoolPts;   // 校园表现分数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditPts() {
        return creditPts;
    }

    public void setCreditPts(double creditPts) {
        this.creditPts = creditPts;
    }

    public double getFinancialPts() {
        return financialPts;
    }

    public void setFinancialPts(double financialPts) {
        this.financialPts = financialPts;
    }

    public double getSchoolPts() {
        return schoolPts;
    }

    public void setSchoolPts(double schoolPts) {
        this.schoolPts = schoolPts;
    }
}
