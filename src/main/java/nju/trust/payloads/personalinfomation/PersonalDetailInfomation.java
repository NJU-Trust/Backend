package nju.trust.payloads.personalinfomation;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public class PersonalDetailInfomation {

    //学校分类
    private String schoolClass;
    //专业情况
    private String majorCondition;
    //受教育情况
    private String educationBackground;
    //经济来源
    private String financeSource;
    //学习成绩
    private String GPA;
    //挂科数
    private Integer numNoPass;
    //奖学金
    private List<String> scholarship;
    //科研竞赛获奖情况
    private List<String> researchCompetition;
    //奖励情况
    private List<String> awards;
    //惩罚情况
    private List<String> punishment;
    //学费及住宿费缴纳状况
    private String payment;
    //图书馆借阅还书情况
    private String library;
    //考试作弊的信息
    private String cheating;

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getMajorCondition() {
        return majorCondition;
    }

    public void setMajorCondition(String majorCondition) {
        this.majorCondition = majorCondition;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getFinanceSource() {
        return financeSource;
    }

    public void setFinanceSource(String financeSource) {
        this.financeSource = financeSource;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public Integer getNumNoPass() {
        return numNoPass;
    }

    public void setNumNoPass(Integer numNoPass) {
        this.numNoPass = numNoPass;
    }

    public List<String> getResearchCompetition() {
        return researchCompetition;
    }

    public void setResearchCompetition(List<String> researchCompetition) {
        this.researchCompetition = researchCompetition;
    }

    public List<String> getPunishment() {
        return punishment;
    }

    public void setPunishment(List<String> punishment) {
        this.punishment = punishment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getCheating() {
        return cheating;
    }

    public void setCheating(String cheating) {
        this.cheating = cheating;
    }

    public List<String> getScholarship() {
        return scholarship;
    }

    public void setScholarship(List<String> scholarship) {
        this.scholarship = scholarship;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }
}
