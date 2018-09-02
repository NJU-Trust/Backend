package nju.trust.service;

import nju.trust.dao.target.*;
import nju.trust.payloads.target.LargeTargetFilterRequest;
import nju.trust.payloads.target.SmallTargetFilterRequest;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

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
    private SmallTargetRepository smallTargetRepository;

    @Test
    public void test1() {
        System.out.println(largeTargetRepository.findAll(new LargeTargetSpecification(LargeTargetFilterRequest.testData())));
        System.out.println(smallTargetRepository.findAll(new SmallTargetSpecification(SmallTargetFilterRequest.testData())));
    }
}