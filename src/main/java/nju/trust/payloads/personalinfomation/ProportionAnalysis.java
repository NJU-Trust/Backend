package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 比例分析
 * @Date: 2018/10/23
 */
public class ProportionAnalysis {
    private static final String NOTCARE = "/";

    private String outcome; // 月支出
    private String adjust;  // 月可调支出
    private String food;    // 月饮食支出
    private ProportionOutcome data1;  // 支出模块
    private ProportionAdjust data2; // 可调支出
    private ProportionFood data3;   // 饮食支出

    public ProportionAnalysis() {
        outcome = NOTCARE;
        adjust = NOTCARE;
        food = NOTCARE;
        data1 = new ProportionOutcome();
        data2 = new ProportionAdjust();
        data3 = new ProportionFood();
    }

    public ProportionAnalysis(double outcome, double adjust, double food, ProportionOutcome data1, ProportionAdjust data2, ProportionFood data3) {
        this.outcome = toForm(outcome);
        this.adjust = toForm(adjust);
        this.food = toForm(food);
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = toForm(outcome);
    }

    public String getAdjust() {
        return adjust;
    }

    public void setAdjust(double adjust) {
        this.adjust = toForm(adjust);
    }

    public String getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = toForm(food);
    }

    public ProportionOutcome getData1() {
        return data1;
    }

    public void setData1(ProportionOutcome data1) {
        this.data1 = data1;
    }

    public ProportionAdjust getData2() {
        return data2;
    }

    public void setData2(ProportionAdjust data2) {
        this.data2 = data2;
    }

    public ProportionFood getData3() {
        return data3;
    }

    public void setData3(ProportionFood data3) {
        this.data3 = data3;
    }

    private String toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num))+"";
    }
}
