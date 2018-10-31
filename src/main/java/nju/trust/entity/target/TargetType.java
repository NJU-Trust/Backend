package nju.trust.entity.target;

public enum TargetType {

    /**
     * Small short-term target
     */
    SMALL("SMALL"),

    /**
     * Large long-term target
     */
    LARGE("LARGE");

    private final String str;

    TargetType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public static TargetType getEnum(String str) {
        TargetType[] types = TargetType.values();
        for(TargetType type : types) {
            if(type.getStr().equals(str)) {
                return type;
            }
        }
        return null;
    }
}
