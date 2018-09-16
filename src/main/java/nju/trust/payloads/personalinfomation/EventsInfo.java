package nju.trust.payloads.personalinfomation;

import java.util.Date;

/**
 * @Author: 161250127
 * @Description: 收款/还款的消息提示
 * @Date: 2018/9/12
 */
public class EventsInfo implements Comparable<EventsInfo> {

    private String date;  //发生日期 格式：2018/9/8

    private EventType title;   //类型 ：已还款|已收款   // TODO enum

    private String description;     //描述

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventType getTitle() {
        return title;
    }

    public void setTitle(EventType title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(EventsInfo eventsInfo) {
        if(this.getTitle().equals(EventType.HAVEGOT) && eventsInfo.getTitle().equals(EventType.HAVEPAY)) {
            return -1;
        }else if(eventsInfo.getTitle().equals(EventType.HAVEGOT) && this.getTitle().equals(EventType.HAVEPAY)) {
            return 1;
        }

        if(this.getTitle().equals(EventType.HAVEGOT)) {
            if(this.getDate().compareTo(eventsInfo.getDate()) > 0) {
                return 1;
            }else {
                return -1;
            }
        }

        if(this.getTitle().equals(EventType.HAVEPAY)) {
            if(this.getDate().compareTo(eventsInfo.getDate()) > 0) {
                return -1;
            }else {
                return 1;
            }
        }
        return 0;
    }
}
