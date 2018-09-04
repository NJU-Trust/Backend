package nju.trust.service.admin;

import nju.trust.entity.CheckItem;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/3
 */
public class ScoreCalUtil {
    private CheckItem checkItem;

    public ScoreCalUtil(CheckItem checkItem) {
        this.checkItem = checkItem;
    }

    // 计算得分并在后台更新
    public void calScore() {
        switch (checkItem) {
            case VOLUNTEERTIME:
                calVolunteerScore();
                break;
        }
    }

    // 计算每年平均志愿活动时长加分
    private void calVolunteerScore() {
        // TODO int score =
    }
}
