package nju.trust.payloads.personalinfomation;

import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 数值分析中每月的信息
 * @Date: 2018/10/23
 */
public class MonthAnalysis {
    private String month;           // 月份：2018-1
    private double income;          // 收入
    private double expense;         // 支出
    private double expense_rig;     // 月刚性支出 = 月日常支出 + 月学习支出 + 月饮食支出
    private double expense_disc;    // 月可调支出 = 月总支出 - 月刚性支出
    private double surplus;         // TODO 投资额结余
    private double lblt;            // 负债=花呗+平台内本月需还款金额
    private double asset;           // 净资产

    public MonthAnalysis() {}

    public MonthAnalysis(LocalDate date) {
        this.month = toForm(date);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(LocalDate date) {
        this.month = toForm(date);
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = toForm(income);
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = toForm(expense);
    }

    public double getExpense_rig() {
        return expense_rig;
    }

    public void setExpense_rig(double expense_rig) {
        this.expense_rig = toForm(expense_rig);
    }

    public double getExpense_disc() {
        return expense_disc;
    }

    public void setExpense_disc(double expense_disc) {
        this.expense_disc = toForm(expense_disc);
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = toForm(surplus);
    }

    public double getLblt() {
        return lblt;
    }

    public void setLblt(double lblt) {
        this.lblt = toForm(lblt);
    }

    public double getAsset() {
        return asset;
    }

    public void setAsset(double asset) {
        this.asset = toForm(asset);
    }
    // 得到月份格式：2018-1
    private String toForm(LocalDate date) {
        return date.getYear()+"-"+date.getMonthValue();
    }
    // 保留两位小数
    private double toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num));
    }
}
