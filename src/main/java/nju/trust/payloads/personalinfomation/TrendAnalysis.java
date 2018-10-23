package nju.trust.payloads.personalinfomation;

import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 趋势分析
 * 月份格式：2018-1
 * 其他格式：xx.xx%
 * @Date: 2018/10/23
 */
public class TrendAnalysis {
    private static final String NOTCARE = "/";

    private String month;                       // 月份
    private String engel = NOTCARE;             // 恩格尔系数
    private String rig_ratio = NOTCARE;         // 刚性比率
    private String d2a_ratio = NOTCARE;         // 负债率
    private String dp_ability = NOTCARE;        // 偿债能力
    private String leverage = NOTCARE;          // 杠杆比率
    private String consump_ratio = NOTCARE;     // 消费比率
    private String saving_ratio = NOTCARE;      // 储蓄比率

    public TrendAnalysis() {}
    public TrendAnalysis(LocalDate date) {
        this.month = toForm(date);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(LocalDate date) {
        this.month = toForm(date);
    }

    public String getEngel() {
        return engel;
    }

    public void setEngel(double engel) {
        this.engel = toForm(engel);
    }

    public String getRig_ratio() {
        return rig_ratio;
    }

    public void setRig_ratio(double rig_ratio) {
        this.rig_ratio = toForm(rig_ratio);
    }

    public String getD2a_ratio() {
        return d2a_ratio;
    }

    public void setD2a_ratio(double d2a_ratio) {
        this.d2a_ratio = toForm(d2a_ratio);
    }

    public String getDp_ability() {
        return dp_ability;
    }

    public void setDp_ability(double dp_ability) {
        this.dp_ability = toForm(dp_ability);
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(double leverage) {
        this.leverage = toForm(leverage);
    }

    public String getConsump_ratio() {
        return consump_ratio;
    }

    public void setConsump_ratio(double consump_ratio) {
        this.consump_ratio = toForm(consump_ratio);
    }

    public String getSaving_ratio() {
        return saving_ratio;
    }

    public void setSaving_ratio(double saving_ratio) {
        this.saving_ratio = toForm(saving_ratio);
    }

    private String toForm(LocalDate date) {
        return date.getYear()+"-"+date.getMonthValue();
    }
    // 保留两位小数
    private String toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num*100))+"%";
    }
}
