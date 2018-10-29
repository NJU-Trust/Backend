package nju.trust.web.admin;

import nju.trust.entity.record.ApproveResult;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.PendingTargetBriefInfo;
import nju.trust.payloads.admin.PendingTargetDetailInfo;
import nju.trust.payloads.admin.TargetAdminBriefInfo;
import nju.trust.payloads.admin.TargetAdminDetailInfo;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<TargetAdminBriefInfo> seeTarget(TargetState state, TargetType type) {
        return adminService.seeTarget(state, type);
    }

    // 查看项目详情
    @GetMapping(value = "/detailInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public TargetAdminDetailInfo seeTarget(Long id) {
        return adminService.seeTarget(id);
    }

    // 项目审批
    // TODO 不要 获取待审核标的列表，分页显示
    // TODO type str LARGE SMALL
    @GetMapping(value = "/pendinglist")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PendingTargetBriefInfo> getPendingTargets(TargetType type) {
        return adminService.getPendingTargets(type);
    }

    //TODO 和上一个合并 查看标的详情
    @GetMapping(value = "/targetpendingitem")
    @PreAuthorize("hasRole('ADMIN')")
    public PendingTargetDetailInfo getPendingTargetDetailInfo(Long targetID){
        return adminService.getPendingTarget(targetID);
    }

    // 标的审核结果
    @GetMapping(value = "/targetcheck")
    @PreAuthorize("hasRole('ADMIN')")
    // TODO result “通过”
    public ApiResponse approveTarget(Long targetId, ApproveResult result) {
        return adminService.approveTarget(targetId, result);
    }
}
