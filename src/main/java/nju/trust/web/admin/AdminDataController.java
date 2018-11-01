package nju.trust.web.admin;

import nju.trust.payloads.admin.BaseStatistics;
import nju.trust.payloads.admin.BreakContractStatistics;
import nju.trust.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 管理员后台数据统计的controller
 * @Date: 2018/9/11
 */
@RestController
@RequestMapping("/adminData")
public class AdminDataController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    // 统计当天的基础数据
    @GetMapping(value = "/base")
    //@PreAuthorize("hasRole('ADMIN')")
    public BaseStatistics getBaseStatistics() {
        return adminService.getBaseStatistics();
    }

    // 统计当月的违约信息
    @GetMapping(value = "/breakContract")
   // @PreAuthorize("hasRole('ADMIN')")
    public BreakContractStatistics getBreakContractStatistics() {
        return adminService.getBreakContractStatistics();
    }
}
