package nju.trust.config;

import nju.trust.dao.user.CreditRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.CreditRating;
import nju.trust.entity.user.CreditRecord;
import nju.trust.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 每月1号3点记录用户上个月的信用分数
 * @Date: 2018/10/24
 */
@Configuration // 主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 开启定时任务
public class CreditRecordConfig {
    private CreditRecordRepository creditRecordRepository;
    private UserRepository userRepository;
    @Autowired
    public CreditRecordConfig(CreditRecordRepository creditRecordRepository, UserRepository userRepository) {
        this.creditRecordRepository = creditRecordRepository;
        this.userRepository = userRepository;
    }

    // 定时任务（每月1号3点）
    @Scheduled(cron = "0 0 3 1 * ?")
    public void saveCredits() {
        LocalDate date = LocalDate.now();

        List<CreditRecord> creditRecords = new ArrayList<>();
        List<User> users = (List<User>)userRepository.findAll();

        for(User user : users) {
            if(!user.getUsername().toLowerCase().equals("admin") && user.getCreditScore() != null && user.getCreditScore() >= 0) {
                double creditScore = user.getCreditScore();
                CreditRating creditRating = user.getCreditRating();
                CreditRecord creditRecord = new CreditRecord(user, date, creditScore, creditRating);
                creditRecords.add(creditRecord);
            }
        }

        creditRecordRepository.saveAll(creditRecords);
    }
}
