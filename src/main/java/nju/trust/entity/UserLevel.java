package nju.trust.entity;

public enum UserLevel {
    /**
     * First level : need email and phone number
     */
    PRIMARY,

    /**
     * Second level : need student number, major, grade,
     * student card photo and school card photo
     */
    INTERMEDIATE,

    /**
     * Third level : need id number, id card photo and
     * some other authentication information
     */
    COMPLETE,

    /**
     * School fellow account : need diploma photo
     */
    SF
}
