package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.InvestAndLoan;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface PersonalInformationService {
    /**
     *
     * @param username 用户名
     * @return
     */
    InvestAndLoan getInvestAndLoanInfo(String username);
}
