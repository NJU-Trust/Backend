package nju.trust.payloads.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import nju.trust.entity.CheckState;
import nju.trust.entity.UserLevel;
import nju.trust.payloads.target.TargetBriefInfo;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/9
 */
public class ProfileResponse {

    @JsonUnwrapped
    private UserInformation basicInformation;

    private List<TargetBriefInfo> favoriteTargets;

    private List<TargetBriefInfo> participatingTargets;

    private CreditCheck creditCheck;

    private UserLevel level;

    @Override
    public String toString() {
        return "ProfileResponse{" +
                "basicInformation=" + basicInformation +
                ", favoriteTargets=" + favoriteTargets +
                ", participatingTargets=" + participatingTargets +
                ", creditCheck=" + creditCheck +
                ", level=" + level +
                '}';
    }

    /**
     * Author: J.D. Liao
     * Date: 2018/8/13
     */
    public static class CreditCheck {

        private UserLevel currentLevel;

        private UserLevel appliedLevel;

        private CheckState checkState;

        public CreditCheck(UserLevel currentLevel, UserLevel appliedLevel, CheckState checkState) {
            this.currentLevel = currentLevel;
            this.appliedLevel = appliedLevel;
            this.checkState = checkState;
        }

        @Override
        public String toString() {
            return "CreditCheck{" +
                    "currentLevel=" + currentLevel +
                    ", appliedLevel=" + appliedLevel +
                    ", checkState=" + checkState +
                    '}';
        }
    }
}
