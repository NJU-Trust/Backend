package nju.trust.service;

import nju.trust.entity.IdentityOption;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.user.*;
import org.springframework.stereotype.Service;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ApiResponse applyIntermediateUser(ApplyIntermediateUserRequest request, String username) {
        return null;
    }

    @Override
    public ApiResponse applyCompleteUser(ApplyCompleteUserRequest request, String username) {
        return null;
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
}
