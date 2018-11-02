package nju.trust.service;

import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.*;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.user.User;
import nju.trust.payloads.target.BadTarget;
import nju.trust.payloads.target.LargeTargetFilterRequest;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import nju.trust.service.target.TargetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.persistence.EntityManager;
import java.io.File;
import java.net.InetAddress;
import java.time.LocalDate;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/27
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetServiceImplTest {

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private LargeTargetRepository largeTargetRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TargetService targetService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvestmentRecordRepository investmentRecordRepository;

    @Autowired
    private RepaymentRecordRepository repaymentRecordRepository;

    @Autowired
    private SmallTargetRepository smallTargetRepository;

    @Test
    public void test1() {
        System.out.println(largeTargetRepository.findAll(new LargeTargetSpecification(LargeTargetFilterRequest.testData())));
        System.out.println(smallTargetRepository.findAll(new SmallTargetSpecification(SmallTargetFilterRequest.testData())));
    }

    @Test
    public void test2() {
        System.out.println(smallTargetRepository.findByNameLike("帽"));
    }

    @Test
    public void getBadTargets() {
        prepareData();

        String username = "cross1";
        Double moneyUpper = null;
        Double moneyLower = null;
        String state = "已还款";
        List<BadTarget> badTargets = targetService.getBadTargets(username, moneyUpper, moneyLower, state);
        print(badTargets);
    }
    private void prepareData() {
        User user = userRepository.findByUsername("cross1").get();
        BaseTarget target = targetRepository.findById((long)1).get();
        InvestmentRecord investmentRecord = new InvestmentRecord(user, target, 100.0);
        investmentRecordRepository.save(investmentRecord);

        RepaymentRecord repaymentRecord = repaymentRecordRepository.findById((long)1).get();
        repaymentRecord.setReturnDate(LocalDate.of(2018, 9, 1));
        repaymentRecordRepository.save(repaymentRecord);
        repaymentRecord = repaymentRecordRepository.findById((long)2).get();
        repaymentRecord.setReturnDate(LocalDate.of(2018, 8, 1));
        repaymentRecordRepository.save(repaymentRecord);
    }


    private void print(List<BadTarget> badTargets) {
        System.out.println("badTargets:");
        for(BadTarget badTarget : badTargets) {
            print(badTarget);
            System.out.println();
        }
    }
    private void print(BadTarget badTarget) {
        System.out.println("targetId:"+badTarget.getTargetId());
        System.out.println("projectName:"+badTarget.getProjectName());
        System.out.println("loanFrom:"+badTarget.getLoanFrom());
        System.out.println("badStartDate:"+badTarget.getBadStartDate());
        System.out.println("investAmount:"+badTarget.getInvestAmount());
        System.out.println("lossAmount:"+badTarget.getLossAmount());
        System.out.println("state:"+badTarget.getState());
    }

}