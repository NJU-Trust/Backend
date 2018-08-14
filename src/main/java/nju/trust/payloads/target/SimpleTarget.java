package nju.trust.payloads.target;

/**
 * Author: J.D. Liao
 * Date: 2018/8/13
 */
public class SimpleTarget {

    private String name;

    private String id;

    public SimpleTarget(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "SimpleTarget{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
