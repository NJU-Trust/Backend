package nju.trust.service.admin;

import nju.trust.dao.admin.RepaymentReposity;
import nju.trust.entity.user.Repayment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.soap.Addressing;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 用于计算用户的逾期数目
 * @Date: 2018/8/30
 */
// Repayment中的startDate（开始时间）repaymentDuration（还款期限）
public class OverdueCalUtil {
    @Autowired
    private RepaymentReposity repaymentReposity;

    private String username = "";
    private LocalDate today;
    private List<Repayment> records;
    private boolean hasGetInfo = false;

    public OverdueCalUtil(String username) {
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
        records = repaymentReposity.getRepaymentByUsername(username);
    }

    /**
     * 判断用户是否有逾期情况存在
     * 即：是否为逾期用户
     * @return true：是逾期用户
     *         false：不是逾期用户
     */
    public boolean isOverdue() {
        // TODO test
        check();

        for(int i = 0 ; i < records.size() ; i++) {
            Repayment record = records.get(i);
            LocalDate begin = record.getStartDate();
            int length = (int)(today.toEpochDay() - begin.toEpochDay());
            if(length > record.getRepaymentDuration()) {// 逾期
                return true;
            }
        }
        return false;
    }


}
