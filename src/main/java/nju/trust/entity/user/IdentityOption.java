package nju.trust.entity.user;

/**
 * 参考《不同信息披露层级设计》"
 */
public enum  IdentityOption {
    ONE("第一层级"),
    TWO("第二层级"),
    THREE("第三层级"),
    FOUR("第四层级");

    private final String str;

    IdentityOption(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
