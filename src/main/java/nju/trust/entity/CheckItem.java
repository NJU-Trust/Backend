package nju.trust.entity;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户信息审核时的细分条目
 * 以2 项目策略.docx中 2.0.3非结构化数据 中 表1 为准
 * 仅包含 表2 中的内容
 * @Date: 2018/8/29
 */
public enum CheckItem {
    VOLUNTEERTIME("每年平均志愿活动时长"),    // 对应于表2 志愿活动时长     double
    STUDENTWORK("学生工作"),                // 对应于表2 学生工作         string(enum)
    REWARD("奖励"),                         // 对应于表2 奖励信息         string(enum)
    MATCH("科研竞赛获奖情况"),               // 对应于表2 科研竞赛获奖信息  string(enum)
    SCHOLARSHIP("奖学金"),                  // 对应于表2 奖学金           string(enum)
    SCHOOLTYPE("学校分类"),                 // 对应于表2 学校分类           string(enum)
    MAJOR("所在专业情况"),                   // 对应于表2 所在专业情况      string(enum)
    EDUCATION("受教育情况"),                 // 对应于表2 受教育情况       string(enum)
    FAILNUM("挂科数"),                      // 对应于表2 挂科数           int
    STUDY("学习成绩"),                      // 对应于表2 成绩排名           int
    DISCREDIT("是否为失信人员"),             // 对应于表2 是否为失信人员      int
    PUNISHMENT("违纪或治安处罚等不良信息"),   // 对应于表2 违纪或治安处罚等不良信息 int
    TESTCHEAT("考试作弊"),                  // 对应于表2 考试作弊           int
    PAYMENT("学费及住宿费的缴纳情况"),        // 对应于表2 学费及住宿费的缴纳情况 int
    REPAYMENT("贷款偿还"),                  // 对应于表2 贷款等偿还信息       int
    RETURNBOOKS("图书馆借阅还书情况"),       // 对应于表2 图书借还信息         int
    SELFINFO("个人信息");

    private String itemStr;

    CheckItem(String str) {
        itemStr = str;
    }

    public String getItemStr() {
        return itemStr;
    }

    public Map<String, Object> toMap() {
        return ImmutableMap.<String, Object>builder()
                .put("str", itemStr)
                .build();
    }
}
