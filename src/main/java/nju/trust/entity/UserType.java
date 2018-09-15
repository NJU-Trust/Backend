package nju.trust.entity;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @Author: 许杨
 * @Description: 在管理员进行用户管理时的查询用户选项
 * @Date: 2018/8/26
 */
public enum UserType {
    // 借款用户：
    NOLOAN("无借款"),     // 无借款用户
    HAVELOAN("待还款"),   // 待还款用户
    OVERDUE("逾期"),    // 逾期用户
    // 投资用户:
    INVESTMENT("投资"); // 投资用户

    private final String str;

    UserType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
    
    public Map<String, Object> toMap() {
        return ImmutableMap.<String, Object>builder()
                    .put("str", str)
                    .build();
    }
}
