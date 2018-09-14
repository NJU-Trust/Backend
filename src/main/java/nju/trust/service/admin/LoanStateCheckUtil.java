package nju.trust.service.admin;

import nju.trust.dao.admin.RepaymentRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.UserType;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用于判定用户的借款数目
 * @Date: 2018/8/30
 */
// Repayment中的startDate（开始时间）repaymentDuration（还款期限）
@Service
public class LoanStateCheckUtil {
    private RepaymentRepository repaymentRepository;
    private UserRepository userRepository;

    private String username;
    private LocalDate today;
    private List<Repayment> records;
    private boolean hasGetInfo;

    @Autowired
    public LoanStateCheckUtil(RepaymentRepository repaymentRepository, UserRepository userRepository) {
        this.repaymentRepository = repaymentRepository;
        this.userRepository = userRepository;
    }

    public void setUsername(String username) {
        this.username = username;
        today = LocalDate.now();
        hasGetInfo = false;
    }
    // 检查用户借款信息是否已经收集
    private void check() {
        if(!hasGetInfo) {
            getInfo();
        }
    }
    // 查找用户的借款信息
    private void getInfo() {
        User user = userRepository.findByUsername(username).get();
        if(!repaymentRepository.existsByUser(user)) {
            records = new ArrayList<>();
        }else {
            records = repaymentRepository.getRepaymentByUser(user);
        }
    }

    /**
     * 判断用户的借款情况（无借款、待还款、逾期）
     * @return UserType
     */
    public UserType checkUserType() {
        check();
        boolean haveLoan = false;    // 用户是否存在还款

        if(records == null || records.size() == 0) {
            return UserType.NOLOAN;
        }

        for (Repayment record : records) {
            LocalDate begin = record.getStartDate();
            if(begin == null) {
                continue;
            }
            int length = (int) (today.toEpochDay() - begin.toEpochDay());
            if (length > record.getDuration()) {// 逾期
                return UserType.OVERDUE;
            }
            if (!haveLoan && record.getRemainingAmount() > 0) {   // 存在待还款
                haveLoan = true;
            }
        }

        if(haveLoan) {
            return UserType.HAVELOAN;
        }else {
            return UserType.NOLOAN;
        }
    }
}
