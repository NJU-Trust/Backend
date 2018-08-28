package nju.trust.entity;

/**
 * @Author: 许杨
 * @Description: 在管理员进行用户管理时的查询用户选项
 * @Date: 2018/8/26
 */
public enum UserType {
    // 借款用户：
    NOLOAN,     // 无借款用户
    HAVELOAN,   // 待还款用户
    OVERDUE,    // 逾期用户
    // 投资用户:
    INVESTMENT; // 投资用户
}
