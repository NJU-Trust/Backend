package nju.trust.payloads.personalinfomation;

import java.util.Date;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/12
 */
public class EventsInfo {

    private Date date;  //发生日期

    private String title;   //类型 ：已还款|已收款

    private String description;     //描述

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
