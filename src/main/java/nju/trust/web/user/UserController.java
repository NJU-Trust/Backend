package nju.trust.web.user;

import nju.trust.entity.UserLevel;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.JwtAuthenticationResponse;
import nju.trust.payloads.LoginRequest;
import nju.trust.security.JwtTokenProvider;
import nju.trust.service.UserService;
import nju.trust.util.APIContext;
import nju.trust.util.CitiAuthorizationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/8/2
 * @Todo:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private UserService userService;

    private APIContext apiContext;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public void setApiContext(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('SF')")
    public ResponseEntity<ApiResponse> test() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "You are "
                + UserLevel.getUserLevelTrans(UserLevel.SF)), HttpStatus.ACCEPTED);
    }


    @PostMapping(value = "/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        CitiAuthorizationHelper.getRealAccessToken( loginRequest.getUsername(), loginRequest.getPassword(), apiContext);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, apiContext.getRealAccessToken() ,loginRequest.getUsername(), userService.getRoles(loginRequest.getUsername())));
    }

//    @PostMapping(value = "/signup")
//    public ApiResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        return userService.addUser(signUpRequest);
//    }
}
