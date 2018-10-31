package nju.trust.payloads;

import nju.trust.entity.user.RoleName;

import java.util.ArrayList;
import java.util.Set;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/9
 * @Todo:
 */
public class JwtAuthenticationResponse {
    private String accessToken;
    private String realAccessToken;
    private String tokenType = "Bearer";
    private ArrayList<String> roles;
    private String username;


    public JwtAuthenticationResponse(String accessToken, String realAccessToken, String username, Set<RoleName> rolesSet) {
        this.accessToken = accessToken;
        this.username = username;
        this.realAccessToken = realAccessToken;
        this.roles = new ArrayList<String>();
        for(RoleName roleName: rolesSet) {
            roles.add(roleName.toString());
        }
    }

    public String getRealAccessToken() {
        return realAccessToken;
    }

    public void setRealAccessToken(String realAccessToken) {
        this.realAccessToken = realAccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}