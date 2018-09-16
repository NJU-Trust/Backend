package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.PersonalBaseInfo;
import org.springframework.stereotype.Service;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/16
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {
    @Override
    public PersonalBaseInfo getBaseInfo(String username) {
        PersonalBaseInfo personalBaseInfo = new PersonalBaseInfo();
        personalBaseInfo.setUsername(username);
        return personalBaseInfo;
    }
}
