package nju.trust.web.user;

import nju.trust.dao.PrimaryUserDao;
import nju.trust.entity.UserLevel;
import nju.trust.entity.user.BaseUser;
import nju.trust.entity.user.PrimaryUser;
import nju.trust.entity.user.RoleName;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.*;
import nju.trust.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

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
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PrimaryUserDao userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('SF')")
    public String test() {
        return "test";
    }


    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    /**
     *
     * @param signUpRequest
     * @attention mock method
     * @return
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        PrimaryUser user = new PrimaryUser();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        ArrayList<RoleName> roles = new ArrayList<>();
        roles.add(RoleName.ROLE_PRIMARY);
        roles.add(RoleName.ROLE_INTERMEDIATE);
        roles.add(RoleName.ROLE_SF);
        user.setRoles(roles);
        user.setUserLevel(UserLevel.PRIMARY);
        userRepository.save(user);
        return new ResponseEntity(new ApiResponse(true, "User registered successfully"),
                HttpStatus.ACCEPTED);
    }
}
