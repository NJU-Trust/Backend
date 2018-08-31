package nju.trust.payloads;

/**
 * Author: J.D. Liao
 * Date: 2018/8/30
 * Description:
 */
public class Range<T extends Comparable<T>> {

    private final T lower;

    private final T upper;

    public Range(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public boolean inRange(T t) {
        return t.compareTo(lower) >= 0 && t.compareTo(upper) <= 0;
    }

    public T getLower() {
        return lower;
    }

    public T getUpper() {
        return upper;
    }
}
