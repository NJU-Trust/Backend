package nju.trust.web.admin;

import nju.trust.entity.UserType;
import nju.trust.entity.record.ApproveResult;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.UserCheckResponse;
import nju.trust.payloads.admin.UserListRequest;
import nju.trust.payloads.admin.UserStateList;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员进行用户管理、用户信息审批
 * @Date: 2018/9/11
 */
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    // 用户管理
    @GetMapping(value = "/manageLen")
    public int getUserListLen(UserListRequest request) {
        String keyword = request.getKeyword();
        UserType type = request.getType();
        return adminService.getUserListLen(keyword, type);
    }


    @GetMapping(value = "/manage")
    public List<UserSimpleInfo> getUserList(UserListRequest request) {
        String keyword = request.getKeyword();
        UserType type = request.getType();
        return adminService.getUserList(keyword, type);
    }

    // 用户审批
    // 获取待审核的用户列表，分页显示
    @GetMapping(value = "/userlist")
    public List<UserStateList> getUserStateList() {
        return adminService.getUserStateList();
    }

    // 审核用户非结构化信息
    @GetMapping(value = "/userpendingitem")
    public UserCheckResponse getUserCheckItems(String username) {
        return adminService.getUserCheckItems(username);
    }

    // 用户信息审核结果，返回审核结果
    @GetMapping(value = "/usercheck")
    public ApiResponse approveItem(String username, Long id, ApproveResult result) {
        return adminService.approveItem(username, id, result);
    }
}
