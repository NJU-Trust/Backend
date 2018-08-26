package nju.trust.service;

import nju.trust.payloads.user.UserSimpleInfo;

import java.util.ArrayList;

/**
 * @Author: 161250127
 * @Description: 用于【管理员】用户管理
 * @Date: 2018/8/26
 */
public interface AdminService {
    public ArrayList<UserSimpleInfo> getUserList();
}
