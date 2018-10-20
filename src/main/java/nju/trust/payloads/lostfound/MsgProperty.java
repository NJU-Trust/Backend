package nju.trust.payloads.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public enum MsgProperty {
    FOUND("失物招领"),
    LOST("寻物启事");

    private String str;

    MsgProperty(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static MsgProperty getMsgProperty(String str) {
        MsgProperty[] msgProperties = MsgProperty.values();
        for(MsgProperty property : msgProperties) {
            if(str.equals(property.getStr())) {
                return property;
            }
        }
        return null;
    }
}
