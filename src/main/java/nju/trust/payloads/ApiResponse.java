package nju.trust.payloads;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/10
 * @Todo:
 */
public class ApiResponse {

    public static ApiResponse successResponse() {
        return new ApiResponse(true, "");
    }

    public static ApiResponse serverGoesWrong() {
        return new ApiResponse(false, "Something is wrong with the server");
    }

    private Boolean success;

    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
