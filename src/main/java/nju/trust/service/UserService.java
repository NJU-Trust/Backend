package nju.trust.service;

import nju.trust.entity.IdentityOption;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.user.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Get user details. This method should be
     * used when a user want to see his own information.
     *
     * @param username username
     * @return user details
     */
    ProfileResponse getUserProfile(String username);

    /**
     * Get user information. This method should be used
     * when other want to see this user's information.
     * @param username target username
     * @param identityOption degree of identity transparency
     * @return user information
     */
    UserInformation getUserInformation(String username, IdentityOption identityOption);

    /**
     * Submit an application to upgrade to an intermediate
     * user to the server
     *
     * @param request  information for intermediate user
     * @param username username
     * @return result of submitting the request to the server
     */
    ApiResponse applyIntermediateUser(ApplyIntermediateUserRequest request, String username);

    /**
     * Submit an application to upgrade to an complete
     * user to the server
     *
     * @param request  information for complete user
     * @param username username
     * @return result of submitting the request to the server
     */
    ApiResponse applyCompleteUser(ApplyCompleteUserRequest request, String username);

    /**
     * Submit an application to upgrade to an schoolFellow
     * user to the server
     *
     * @param request  information for schoolFellow user
     * @param username username
     * @return result of submitting the request to the server
     */
    ApiResponse applySchoolFellow(ApplySchoolFellowRequest request, String username);
}
