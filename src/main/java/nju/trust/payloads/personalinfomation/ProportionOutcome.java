package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 比例分析的支出模块
 * @Date: 2018/10/23
 */
public class ProportionOutcome {
    private static final String NOTCARE = "/";

    private String daily;   // 日常
    private String learning;// 学习
    private String food;    // 饮食（总饮食）
    private String travel;  // 出行
    private String fun;     // 娱乐

    public ProportionOutcome() {
        daily = NOTCARE;
        learning = NOTCARE;
        food = NOTCARE;
        travel = NOTCARE;
        fun = NOTCARE;
    }

    public ProportionOutcome(double daily, double learning, double food, double travel, double fun) {
        this.daily = toForm(daily);
        this.learning = toForm(learning);
        this.food = toForm(food);
        this.travel = toForm(travel);
        this.fun = toForm(fun);
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily = toForm(daily);
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(double learning) {
        this.learning = toForm(learning);
    }

    public String getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = toForm(food);
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(double travel) {
        this.travel = toForm(travel);
    }

    public String getFun() {
        return fun;
    }

    public void setFun(double fun) {
        this.fun = toForm(fun);
    }



    private String toForm(double num) {
        return Double.parseDouble(String.format("%.2f", num))+"";
    }

    public void add(double daily, double learning, double food, double travel, double fun) {
        setDaily(Double.parseDouble(this.daily) + daily);
        setLearning(Double.parseDouble(this.learning) + learning);
        setFood(Double.parseDouble(this.food) + food);
        setTravel(Double.parseDouble(this.travel) + travel);
        setFun(Double.parseDouble(this.fun) + fun);
    }
}
