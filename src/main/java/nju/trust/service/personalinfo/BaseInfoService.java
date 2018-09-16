package nju.trust.service.personalinfo;

import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.personalinfomation.ChangedPersonalInfo;
import nju.trust.payloads.personalinfomation.PersonalBaseInfo;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/16
 */
public interface BaseInfoService {

    PersonalBaseInfo getBaseInfo(String username);

    ApiResponse changeBaseInfo(ChangedPersonalInfo changedPersonalInfo);
}
