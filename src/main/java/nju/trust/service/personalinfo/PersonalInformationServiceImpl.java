package nju.trust.service.personalinfo;

import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.user.User;
import nju.trust.payloads.personalinfomation.InvestAndLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/11
 */
@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {
    private UserRepository userRepository;
    private InvestmentRecordRepository investmentRecordRepository;
    @Autowired
    public PersonalInformationServiceImpl(UserRepository userRepository, InvestmentRecordRepository investmentRecordRepository) {
        this.userRepository = userRepository;
        this.investmentRecordRepository = investmentRecordRepository;
    }

    // TODO code
    @Override
    public InvestAndLoan getInvestAndLoanInfo(String username) {
        User user = userRepository.findByUsername(username).get();
        InvestAndLoan info = new InvestAndLoan();
        info.setTotalInvestment(calTotalInvestment(username));
        info.setTotalLoan(calTotalLoan(username));
        info.setPendingPrincipal(calPendingPrincipal(username));
        info.setPendingInterest(calPendingInterest(username));
        info.setCreditRating(user.getCreditRating().toString());
        info.setCreditRatingScore(user.getCreditScore());
        return info;
    }
    // TODO test 计算投资总额
    private double calTotalInvestment(String username) {
        List<InvestmentRecord> records = investmentRecordRepository.findAllByUserUsername(username);
        double sum = 0;
        for(InvestmentRecord record : records) {
            sum = sum + record.getInvestedMoney();
        }
        return sum;
    }
    // TODO code
    private double calTotalLoan(String username) {
        return 0;
    }
    // TODO code
    private double calPendingPrincipal(String username) {return 0;}
    // TODO code
    private double calPendingInterest(String username){return 0;}
}
