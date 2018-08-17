package nju.trust.payloads;

/**
 * Author: J.D. Liao
 * Date: 2018/8/15
 */
public class InvestResponse extends ApiResponse {

    private boolean schoolFellow;

    public InvestResponse(Boolean success, String message, boolean schoolFellow) {
        super(success, message);
        this.schoolFellow = schoolFellow;
    }

    @Override
    public String toString() {
        return "InvestResponse{" +
                "schoolFellow=" + schoolFellow +
                '}' + super.toString();
    }

    public boolean isSchoolFellow() {
        return schoolFellow;
    }

    public void setSchoolFellow(boolean schoolFellow) {
        this.schoolFellow = schoolFellow;
    }
}
