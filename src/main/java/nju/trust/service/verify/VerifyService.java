package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.stereotype.Service;

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
}
