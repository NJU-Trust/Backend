package nju.trust.payloads.personalinfomation;

import java.util.List;

/**
 * @Author: 161250127
 * @Description: 校园表现
 * @Date: 2018/9/11
 */
public class CampusPerformance {
    //个人表现数值--学校、学历、社交情况、获奖情况、成绩
    private List<Integer> personalPerformance;
    //平均表现数值
    private List<Integer> averagePerformance;
    //是否超过平均表现
    private boolean aboveAverage;

    public List<Integer> getPersonalPerformance() {
        return personalPerformance;
    }

    public void setPersonalPerformance(List<Integer> personalPerformance) {
        this.personalPerformance = personalPerformance;
    }

    public List<Integer> getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(List<Integer> averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    public boolean isAboveAverage() {
        return aboveAverage;
    }

    public void setAboveAverage(boolean aboveAverage) {
        this.aboveAverage = aboveAverage;
    }
}
