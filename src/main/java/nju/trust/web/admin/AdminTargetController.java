package nju.trust.web.admin;

import nju.trust.entity.record.ApproveResult;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.PendingTargetDetailInfo;
import nju.trust.payloads.admin.TargetAdminBriefInfo;
import nju.trust.payloads.admin.TargetAdminDetailInfo;
import nju.trust.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员进行项目管理、项目审批
 * @Date: 2018/9/11
 */
@RestController
@RequestMapping("/adminTarget")
public class AdminTargetController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    // 项目管理
    // 查看项目概要
    @GetMapping(value = "/briefInfo")
    public List<TargetAdminBriefInfo> seeTarget(String state, String type) {
        TargetState targetState = TargetState.getEnum(state);
        TargetType targetType = TargetType.getEnum(type);
        return adminService.seeTarget(targetState, targetType);
    }

    // 查看项目详情
    @GetMapping(value = "/detailInfo")
    public TargetAdminDetailInfo seeTarget(Long id) {
        return adminService.seeTarget(id);
    }

  /*  // 项目审批
    @GetMapping(value = "/pendinglist")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PendingTargetBriefInfo> getPendingTargets(TargetType type) {
        return adminService.getPendingTargets(type);
    }*/

    // 查看待审核标的详情
    @GetMapping(value = "/pendingTarget")
    public List<PendingTargetDetailInfo> getPending(String targetType) {
        TargetType type = TargetType.getEnum(targetType);
        return adminService.getPendingTarget(type);
    }

    // 标的审核结果
    @GetMapping(value = "/targetcheck")
    public ApiResponse approveTarget(Long targetId, String result) {
        ApproveResult approveResult = ApproveResult.getEnum(result);
        return adminService.approveTarget(targetId, approveResult);
    }
}
