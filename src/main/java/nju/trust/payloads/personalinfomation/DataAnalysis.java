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
    private double expense_rigSum;                  // 总刚性支出
    private double expense_discSum;                 // 总可调支出
    private double surplusSum;                      // 总投资额结余
    private double lbltSum;                         // 总负债
    private double assetSum;                        // 总净资产

    public DataAnalysis() {
        monthAnalysisList = new ArrayList<>();
        incomeSum = 0.00;
        expenseSum = 0.00;
    }

    public DataAnalysis(List<MonthAnalysis> monthAnalysisList, double incomeSum, double expenseSum, double expense_rigSum, double expense_discSum, double surplusSum, double lbltSum, double assetSum) {
        this.monthAnalysisList = monthAnalysisList;
        this.incomeSum = toForm(incomeSum);
        this.expenseSum = toForm(expenseSum);
        this.expense_rigSum = toForm(expense_rigSum);
        this.expense_discSum = toForm(expense_discSum);
        this.surplusSum = toForm(surplusSum);
        this.lbltSum = toForm(lbltSum);
        this.assetSum = toForm(assetSum);
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

    public double getExpense_rigSum() {
        return expense_rigSum;
    }

    public void setExpense_rigSum(double expense_rigSum) {
        this.expense_rigSum = toForm(expense_rigSum);
    }

    public double getExpense_discSum() {
        return expense_discSum;
    }

    public void setExpense_discSum(double expense_discSum) {
        this.expense_discSum = toForm(expense_discSum);
    }

    public double getSurplusSum() {
        return surplusSum;
    }

    public void setSurplusSum(double surplusSum) {
        this.surplusSum = toForm(surplusSum);
    }

    public double getLbltSum() {
        return lbltSum;
    }

    public void setLbltSum(double lbltSum) {
        this.lbltSum = toForm(lbltSum);
    }

    public double getAssetSum() {
        return assetSum;
    }

    public void setAssetSum(double assetSum) {
        this.assetSum = toForm(assetSum);
    }

    // 保留两位小数
    private double toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num));
    }
}
