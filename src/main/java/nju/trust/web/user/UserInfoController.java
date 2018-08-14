package nju.trust.web.user;

import nju.trust.payloads.user.ProfileResponse;
import nju.trust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private UserService userService;

    @RequestMapping("/details")
    public ProfileResponse getUserProfile(Principal principal) {
        String username = principal.getName();
        return userService.getUserProfile(username);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
