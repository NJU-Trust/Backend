package nju.trust.payloads.personalinfomation;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/16
 */
public class Relation {
    private String source;  //
    private String target;  // 关系对应的人物
    private String name;    // 关系的名称
    private String creditChange;    // 信用变化情况

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditChange() {
        return creditChange;
    }

    public void setCreditChange(String creditChange) {
        this.creditChange = creditChange;
    }
}
