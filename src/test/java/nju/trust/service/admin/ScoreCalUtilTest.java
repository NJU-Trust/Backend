
package nju.trust.service.admin;

import nju.trust.dao.admin.UserInfoCheckRecordRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.user.UnstructuredData;
import nju.trust.entity.user.UnstructuredDataType;
import nju.trust.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/9
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ScoreCalUtilTest {
    @Autowired
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    @Autowired
    private ScoreCalUtil test;

    @Test
    // 志愿时长
    public void calScore1() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)4).get();
        test.calScore(record);
    }

    @Test
    // 学生工作
    public void calScore2() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)13).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 奖励
    public void calScore3() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)14).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 科研竞赛获奖情况
    public void calScore4() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)15).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 奖学金
    public void calScore5() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)16).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 学校分类
    public void calScore6() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)17).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 受教育情况
    public void calScore7() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)18).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 挂科数
    public void calScore8() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)19).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 学习成绩
    public void calScore9() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)20).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 考试作弊
    public void calScore10() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)21).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 学费及住宿费的缴纳情况
    public void calScore11() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)22).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 贷款偿还
    public void calScore12() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)23).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 图书馆借阅还书情况
    public void calScore13() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)24).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }

    @Test
    // 失信人员
    public void calScore14() {
        UserInfoCheckRecord record = userInfoCheckRecordRepository.findById((long)2).get();
        if(record == null) {
            System.out.println("record == null");
        }else {
            System.out.println("record != null");
            System.out.println("record id = "+record.getId());
        }
        test.calScore(record);
    }
/*
    @Test
    public void getUnstructuredData1() {
        String username = "weiwei";
        UnstructuredDataType type = UnstructuredDataType.MAJOR;
        double score = test.getUnstructuredData(username, type);
        System.out.println(username+"的得分为："+score);
    }

    @Test
    public void getUnstructuredData2() {
        String username = "weiwei";
        UnstructuredDataType type = UnstructuredDataType.EDUCATION;
        double score = test.getUnstructuredData(username, type);
        System.out.println(username+"的得分为："+score);
    }*/
}
