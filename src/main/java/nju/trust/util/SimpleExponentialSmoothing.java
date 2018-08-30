package nju.trust.util;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2018/8/30
 * Description:
 */
public class SimpleExponentialSmoothing {

    private static final double ALPHA = 0.1;

    public static double forecast(List<Double> data) {
        if (data.size() == 0)
            return 0;

        double[] s = new double[data.size()];
        s[0] = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            s[i] = ALPHA * data.get(i) + (1 - ALPHA) * s[i - 1];
        }

        return s[s.length - 1];
    }
}
