package nju.trust.web.admin;

import nju.trust.entity.UserType;
import nju.trust.payloads.admin.UserListRequest;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/manage")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserSimpleInfo> getUserList(Pageable pageable, @Valid UserListRequest request) {
        String keyword = request.getKeyword();
        UserType type = request.getType();
        return adminService.getUserList(pageable, keyword, type);
    }
}
