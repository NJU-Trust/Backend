package nju.trust.payloads.user;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/28
 * @Todo:
 */
public class BizToken {
    private String eventId;
    private String bizToken;
    private String modulus;
    private String exponent;

    public BizToken(String eventId, String bizToken, String modulus, String exponent) {
        this.eventId = eventId;
        this.bizToken = bizToken;
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getBizToken() {
        return bizToken;
    }

    public void setBizToken(String bizToken) {
        this.bizToken = bizToken;
    }

    public String getModulus() {
        return modulus;
    }

    public void setModulus(String modulus) {
        this.modulus = modulus;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }
}
