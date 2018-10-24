package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 比例分析饮食模块
 * @Date: 2018/10/23
 */
public class ProportionFood {
    private static final String NOTCARE = "/";

    private String schoolCanteen;   // 食堂
    private String takeOut;         // 外卖
    private String eatingOut;       // 外出
    private String snackAndFruit;   // 零食

    public ProportionFood() {
        schoolCanteen = NOTCARE;
        takeOut = NOTCARE;
        eatingOut = NOTCARE;
        snackAndFruit = NOTCARE;
    }

    public ProportionFood(double schoolCanteen, double takeOut, double eatingOut, double snackAndFruit) {
        this.schoolCanteen = toForm(schoolCanteen);
        this.takeOut = toForm(takeOut);
        this.eatingOut = toForm(eatingOut);
        this.snackAndFruit = toForm(snackAndFruit);
    }

    public String getSchoolCanteen() {
        return schoolCanteen;
    }

    public void setSchoolCanteen(double schoolCanteen) {
        this.schoolCanteen = toForm(schoolCanteen);
    }

    public String getTakeOut() {
        return takeOut;
    }

    public void setTakeOut(double takeOut) {
        this.takeOut = toForm(takeOut);
    }

    public String getEatingOut() {
        return eatingOut;
    }

    public void setEatingOut(double eatingOut) {
        this.eatingOut = toForm(eatingOut);
    }

    public String getSnackAndFruit() {
        return snackAndFruit;
    }

    public void setSnackAndFruit(double snackAndFruit) {
        this.snackAndFruit = toForm(snackAndFruit);
    }

    private String toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num))+"";
    }

    public void add(double schoolCanteen, double takeOut, double eatingOut, double snackAndFruit) {
        setSchoolCanteen(Double.parseDouble(this.schoolCanteen)+schoolCanteen);
        setTakeOut(Double.parseDouble(this.takeOut)+takeOut);
        setEatingOut(Double.parseDouble(this.eatingOut)+eatingOut);
        setSnackAndFruit(Double.parseDouble(this.snackAndFruit)+snackAndFruit);
    }
}
