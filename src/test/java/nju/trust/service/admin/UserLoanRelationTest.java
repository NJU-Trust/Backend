package nju.trust.service.admin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description: UserLoanRelation类方法的测试
 * @Date: 2018/10/21
 */
public class UserLoanRelationTest {

    @Test
    public void compareTo() {
        UserLoanRelation relation1 = new UserLoanRelation("test1", 100);
        UserLoanRelation relation2 = new UserLoanRelation("test2", 100);
        assertEquals(-1, relation1.compareTo(relation2));
    }

    @Test
    public void sort() {
        UserLoanRelation relation1 = new UserLoanRelation("test1", 100);
        UserLoanRelation relation2 = new UserLoanRelation("test2", 100);
        UserLoanRelation relation3 = new UserLoanRelation("test3", 50);
        ArrayList<UserLoanRelation> relations = new ArrayList<>();
        relations.add(relation1);   relations.add(relation2);   relations.add(relation3);
        Collections.sort(relations);
        System.out.println("sorted relations:");
        for(UserLoanRelation relation : relations) {
            System.out.println(relation.getUsername()+"  "+relation.getMoney());
        }
    }

    @Test
    public void indexOf() {
        UserLoanRelation relation1 = new UserLoanRelation("test1", 100);
        UserLoanRelation relation2 = new UserLoanRelation("test2", 100);
        UserLoanRelation relation3 = new UserLoanRelation("test3", 50);
        ArrayList<UserLoanRelation> relations = new ArrayList<>();
        relations.add(relation1);   relations.add(relation2);   relations.add(relation3);

        UserLoanRelation relation = new UserLoanRelation("test1");
        assertEquals(0, relations.indexOf(relation));
    }

    @Test
    public void plus1() {
        UserLoanRelation relation1 = new UserLoanRelation("test", 100);
        UserLoanRelation relation2 = new UserLoanRelation("test", 100);
        relation1.plus(relation2);
        assertEquals(200, relation1.getMoney(), 0);
    }
    @Test
    public void plus2() {
        UserLoanRelation relation1 = new UserLoanRelation("test1", 100);
        UserLoanRelation relation2 = new UserLoanRelation("test2", 100);
        relation1.plus(relation2);
        assertEquals(100, relation1.getMoney(), 0);
    }
}