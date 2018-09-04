package nju.trust.service;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.IdentityOption;
import nju.trust.entity.UserLevel;
import nju.trust.entity.user.RoleName;
import nju.trust.entity.user.User;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.Range;
import nju.trust.payloads.SignUpRequest;
import nju.trust.payloads.user.*;
import nju.trust.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponse applyIntermediateUser(ApplyIntermediateUserRequest request, String username) {
        return null;
    }

    @Override
    public ApiResponse addUser(SignUpRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse(false, "Username is already taken!");
        } else if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false, "Email is already taken");
        } else if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return new ApiResponse(false, "PhoneNumber is already taken");
        }

        //TODO: Verify email address
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        ArrayList<RoleName> roles = new ArrayList<>();
        roles.add(RoleName.ROLE_PRIMARY);
        user.setRoles(roles);
        user.setUserLevel(UserLevel.PRIMARY);

        userRepository.save(user);
        return new ApiResponse(true, "User registered successfully");
    }

    @Override
    public ApiResponse applySchoolFellow(ApplySchoolFellowRequest request, String username) {
        return null;
    }

    @Override
    public ProfileResponse getUserProfile(String username) {
        return null;
    }

    @Override
    public UserInformation getUserInformation(String username, IdentityOption identityOption) {
        return null;
    }

    @Override
    public Range<Double> getInterestRange(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        double upper = Constant.BENCHMARK_INTEREST_RATE +
                user.getCreditScore() * (Constant.INTEREST_RATE_UPPER_BOUND - Constant.BENCHMARK_INTEREST_RATE);
        double lower = Constant.BENCHMARK_INTEREST_RATE -
                user.getCreditScore() * (Constant.BENCHMARK_INTEREST_RATE - Constant.INTEREST_RATE_LOWER_BOUND);

        return new Range<>(lower, upper);
    }
}
