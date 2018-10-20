package nju.trust.service.lostfound;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
public interface LostAndFoundService {
    /**
     * @author: 唐佳未
     * @description: 发布信息
     * @param: 界面所填信息
     * @return:
     * @exception:
     */
    public ApiResponse launchTask(TaskInfo lostAndFound);

    /**
     * @author: 唐佳未
     * @description: 查看进行中和已完成任务
     * @param: 任务类别（失物/寻物），任务状态（完成/进行）
     * @return:
     * @exception:
     */
    public List<TaskInfo> getMyTask(String username, MsgProperty msgProperty, ProcessState processState);

    /**
     * @author: 唐佳未
     * @description: 消息大厅
     * @param: 任务类别（失物/寻物），物品分类，地点
     * @return:
     * @exception:
     */
    public List<TaskInfo> findTask(MsgProperty msgProperty, ThingsType thingsType, LostPlace lostPlace);
}
