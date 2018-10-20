package nju.trust.payloads.lostfound;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public enum LostPlace {

    CANTEEN456("四五六食堂"),
    BASICEXPERIMENT("基础实验楼"),
    XIAN1("仙I"),
    XIAN2("仙II"),
    LEFTRIVER("逸夫楼"),
    CANTEEN9("九食堂"),
    CANTEEN10("十食堂"),
    PLAYGROUND("操场"),
    OTHER("其他");

    private String str;

    LostPlace(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }


    public static LostPlace getLostPlace(String str){
        LostPlace[] lostPlaces = LostPlace.values();
        for(LostPlace property : lostPlaces){
            if(str.equals(property.getStr())){
                return property;
            }
        }

        return LostPlace.OTHER;
    }
}
