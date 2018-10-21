package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface VerifyService {
    /**
     * 保存校园验证
     * @param schoolVerifyInfo 校园验证的基本信息
     * @param username 用户名
     * @return
     */
    ApiResponse schoolVerify(SchoolVerifyInfo schoolVerifyInfo, String username);

    /**
     * @author: 唐佳未
     * @description: 保存个性信息
     * @param:
     * @return: ApiResponse
     * @exception:
     */
    ApiResponse selfInfoVerify(String username, int fail, List<String> report_cards, List<NameAndEvidence> school_rewards
            , List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards, List<NameAndEvidence> country_rewards
            , double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications);
}
