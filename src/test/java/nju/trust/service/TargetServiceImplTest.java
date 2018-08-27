package nju.trust.service;

import nju.trust.dao.target.SmallTargetRepository;
import nju.trust.dao.target.SmallTargetSpecification;
import nju.trust.dao.target.TargetRepository;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

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
    private SmallTargetRepository smallTargetRepository;

    @Test
    public void test1() {
        System.out.println(smallTargetRepository.findAll(new SmallTargetSpecification(SmallTargetFilterRequest.testData())));
    }
}