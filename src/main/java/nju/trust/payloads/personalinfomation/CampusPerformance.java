package nju.trust.payloads.personalinfomation;

import java.util.List;

/**
 * @Author: 161250127
 * @Description: 校园表现
 * @Date: 2018/9/11
 */
public class CampusPerformance {
    //个人表现数值--学校、学历、社交情况、获奖情况、成绩
    private List<Double> personalPerformance;
    //平均表现数值
    private List<Double> averagePerformance;
    //是否超过平均表现
    private boolean aboveAverage;

    public List<Double> getPersonalPerformance() {
        return personalPerformance;
    }

    public void setPersonalPerformance(List<Double> personalPerformance) {
        this.personalPerformance = personalPerformance;
    }

    public List<Double> getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(List<Double> averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    public boolean getAboveAverage() {
        return aboveAverage;
    }

    public void setAboveAverage(boolean aboveAverage) {
        this.aboveAverage = aboveAverage;
    }
}
