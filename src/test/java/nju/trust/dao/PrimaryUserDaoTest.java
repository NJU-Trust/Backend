package nju.trust.dao;

import nju.trust.entity.UserLevel;
import nju.trust.entity.user.CompleteUser;
import nju.trust.entity.user.SFUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimaryUserDaoTest {

    private PrimaryUserDao userDao;

    @Autowired
    public void setUserDao(PrimaryUserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void test1() {
        CompleteUser intermediateUser = new CompleteUser();
        intermediateUser.setGrade(2018);
        intermediateUser.setEmail("test@mail.com");
        intermediateUser.setUsername("Excited");
        intermediateUser.setPassword("excited");
        intermediateUser.setUserLevel(UserLevel.SF);
        intermediateUser.setRealName("frog");

        userDao.save(intermediateUser);
    }

    @Test
    public void test2() {
        userDao.findByUsername("Excited").ifPresent(System.out::println);
    }

    @Test
    public void test3() {
        SFUser sfUser = new SFUser();
        sfUser.setUsername("schoolFellow");

        List<String> accomplishments = new ArrayList<>();
        accomplishments.add("one");
        accomplishments.add("two");
        sfUser.setAccomplishment(accomplishments);

        userDao.save(sfUser);
    }
}