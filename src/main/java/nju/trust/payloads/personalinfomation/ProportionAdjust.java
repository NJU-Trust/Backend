package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description: 比例分析的可调支出模块
 * @Date: 2018/10/23
 */
public class ProportionAdjust {
    private String dress;   // 衣物
    private String food;    // 饮食=外卖+外出就餐+零食水果
    private String hotel;   // 住宿
    private String fun;     // 娱乐

    public ProportionAdjust(double dress, double food, double hotel, double fun) {
        this.dress = toForm(dress);
        this.food = toForm(food);
        this.hotel = toForm(hotel);
        this.fun = toForm(fun);
    }

    public String getDress() {
        return dress;
    }

    public void setDress(double dress) {
        this.dress = toForm(dress);
    }

    public String getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = toForm(food);
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(double hotel) {
        this.hotel = toForm(hotel);
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
}
