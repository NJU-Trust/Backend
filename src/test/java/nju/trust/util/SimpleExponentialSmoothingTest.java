package nju.trust.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: J.D. Liao
 * Date: 2018/9/7
 * Description:
 */
public class SimpleExponentialSmoothingTest {

    @Test
    public void test1() {
        List<Double> data = Arrays.asList(80., 78., 90., 94., 100., 103., 120., 130.);
        System.out.println(SimpleExponentialSmoothing.forecast(data));
    }
}