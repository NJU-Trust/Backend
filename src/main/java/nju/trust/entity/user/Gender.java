package nju.trust.entity.user;

/**
 * Author: J.D. Liao
 * Date: 2018/8/18
 * Description:
 */
public enum Gender {
    MALE("男"),
    FEMALE("女");

    private String str;

    Gender(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static Gender getGender(String s) {
        for(Gender gender : Gender.values()) {
            if(s.equals(gender.getStr())) {
                return gender;
            }
        }
        return null;
    }
}
