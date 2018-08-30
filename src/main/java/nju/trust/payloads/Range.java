package nju.trust.payloads;

/**
 * Author: J.D. Liao
 * Date: 2018/8/30
 * Description:
 */
public class Range<T> {

    private final T lower;

    private final T upper;

    public Range(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public T getLower() {
        return lower;
    }

    public T getUpper() {
        return upper;
    }
}
