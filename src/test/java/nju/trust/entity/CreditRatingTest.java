package nju.trust.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/26
 * Description:
 */
public class CreditRatingTest {

    @Test
    public void testAA() {
        assertEquals("AA", CreditRating.of(90.).toString());
    }

    @Test
    public void testA() {
        assertEquals("A", CreditRating.of(85).toString());
    }

    @Test
    public void testD() {
        assertEquals("D", CreditRating.of(39.9).toString());
    }
}