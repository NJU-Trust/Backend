package nju.trust.service.personalinfo;

import nju.trust.payloads.personalinfomation.InvestandLoan;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
public interface PersonalInformationService {

    public InvestandLoan getInvestAndLoanInfo(String username);
}
