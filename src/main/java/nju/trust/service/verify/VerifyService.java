package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.NameAndEvidence;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface VerifyService {
    /**
     * 保存校园验证
     *
     * @param schoolVerifyInfo 校园验证的基本信息
     * @param username         用户名
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


    /**
     * 校友信息验证
     * @param username     用户名
     * @param gender       性别（男 女）
     * @param birthday     出生年月日（2018-01-01）
     * @param idCardNumber 身份证号
     * @param education    学历（本科毕业、研究生毕业、博士毕业）
     * @param evidence     学历证明
     * @param institution  工作单位
     * @param livingPlace  居住地
     * @return 提交是否成功
     */
    ApiResponse alumnaVerify(String username, String gender, String birthday, String idCardNumber, String education, String evidence, String institution, String livingPlace);

    /**
     * 得到用户角色
     * @param username 用户名
     * @return ["初级/非初级/初级审核中","校友/学生"]
     */
    List<String> getRoles(String username);
}