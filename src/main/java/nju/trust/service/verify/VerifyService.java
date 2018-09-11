package nju.trust.service.verify;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.verifyInfo.SchoolVerifyInfo;
import org.springframework.stereotype.Service;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public interface VerifyService {
    /*
    校园验证的基本信息
     */
    public ApiResponse schoolVerify(SchoolVerifyInfo schoolVerifyInfo);
}
