package nju.trust.entity;

import java.util.HashMap;

public enum UserLevel {
    /**
     * First level : need email and phone number
     */
    PRIMARY,

    /**
     * Second level : need student number, major, grade,
     * student card photo and school card photo
     */
    INTERMEDIATE,

    /**
     * Third level : need id number, id card photo and
     * some other authentication information
     */
    COMPLETE,

    /**
     * School fellow account : need diploma photo
     */
    SF,

    /**
     * Discredit user: can't do any operation
     */
    DISCREDIT;


    /**
     *  Emun to string
     */
    private static HashMap<UserLevel, String> userLevelTrans = new HashMap<>();

    static{
        userLevelTrans.put(PRIMARY, "初级账号");
        userLevelTrans.put(INTERMEDIATE, "中级账号");
        userLevelTrans.put(COMPLETE, "完整账号");
        userLevelTrans.put(SF, "校友账号");
        userLevelTrans.put(DISCREDIT, "失信人员");
    }

    public static String getUserLevelTrans(UserLevel userLevel) {
        return userLevelTrans.get(userLevel);
    }

}
