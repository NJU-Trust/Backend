package nju.trust.payloads.admin;

import nju.trust.payloads.ApiResponse;

import java.util.List;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/2
 */
public class UserCheckResponse extends ApiResponse {
    private List<UserCheckItem> haveApproved;
    private List<UserCheckItem> toApprove;

    public UserCheckResponse(Boolean success, String message) {
        super(success, message);
    }

    public UserCheckResponse(Boolean success, String message, List<UserCheckItem> haveApproved, List<UserCheckItem> toApprove) {
        super(success, message);
        this.haveApproved = haveApproved;
        this.toApprove = toApprove;
    }

    public List<UserCheckItem> getHaveApproved() {
        return haveApproved;
    }

    public void setHaveApproved(List<UserCheckItem> haveApproved) {
        this.haveApproved = haveApproved;
    }

    public List<UserCheckItem> getToApprove() {
        return toApprove;
    }

    public void setToApprove(List<UserCheckItem> toApprove) {
        this.toApprove = toApprove;
    }
}
