package nju.trust.payloads.lostfound;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/22
 * @Todo:
 */
public class TaskInfoPage {
    int total;
    List<TaskInfo> taskInfos;

    public TaskInfoPage(int total, List<TaskInfo> taskInfos) {
        this.total = total;
        this.taskInfos = taskInfos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TaskInfo> getTaskInfos() {
        return taskInfos;
    }

    public void setTaskInfos(List<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
    }
}
