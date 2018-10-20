package nju.trust.payloads.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public enum ThingsType {
    CARD("校园卡"),
    KEY("钥匙"),
    CUP("水杯"),
    UMBRELLA("雨伞"),
    OTHER("其他");

    private String str;

    ThingsType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static ThingsType getThingsType(String str){
        ThingsType[] thingsTypes = ThingsType.values();
        for(ThingsType type: thingsTypes){
            if(str.equals(type.getStr())){
                return type;
            }
        }
        return ThingsType.OTHER;
    }
}
