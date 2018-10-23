package nju.trust.payloads.personalinfomation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 数值分析的返回值
 * @Date: 2018/10/23
 */
public class DataAnalysis {
    private List<MonthAnalysis> monthAnalysisList;  // 每月信息
    private double incomeSum;                       // 总收入
    private double expenseSum;                      // 总支出

    public DataAnalysis() {
        monthAnalysisList = new ArrayList<>();
        incomeSum = 0.00;
        expenseSum = 0.00;
    }

    public DataAnalysis(List<MonthAnalysis> monthAnalysisList, double incomeSum, double expenseSum) {
        this.monthAnalysisList = monthAnalysisList;
        this.incomeSum = toForm(incomeSum);
        this.expenseSum = toForm(expenseSum);
    }

    public List<MonthAnalysis> getMonthAnalysisList() {
        return monthAnalysisList;
    }

    public void setMonthAnalysisList(List<MonthAnalysis> monthAnalysisList) {
        this.monthAnalysisList = monthAnalysisList;
    }

    public void addMonthAnalysis(MonthAnalysis monthAnalysis) {
        if(monthAnalysisList == null) {
            monthAnalysisList = new ArrayList<>();
        }
        monthAnalysisList.add(monthAnalysis);
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(double incomeSum) {
        this.incomeSum = toForm(incomeSum);
    }

    public double getExpenseSum() {
        return expenseSum;
    }

    public void setExpenseSum(double expenseSum) {
        this.expenseSum = toForm(expenseSum);
    }

    // 保留两位小数
    private double toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num));
    }
}
