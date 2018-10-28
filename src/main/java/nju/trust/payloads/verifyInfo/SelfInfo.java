package nju.trust.payloads.verifyInfo;

import java.util.List;

/**
 * @Author: 许杨
 * @Description: 个人信息
 * @Date: 2018/10/27
 */
public class SelfInfo {
    private int fail;
    private List<String> report_cards;
    private List<NameAndEvidence> school_rewards;
    private List<NameAndEvidence> city_rewards;
    private List<NameAndEvidence> province_rewards;
    private List<NameAndEvidence> country_rewards;
    private double volunteer;
    private String volunteer_img;
    private List<NameAndEvidence> self_qualifications;

    public SelfInfo() {}

    public SelfInfo(int fail, List<String> report_cards, List<NameAndEvidence> school_rewards, List<NameAndEvidence> city_rewards, List<NameAndEvidence> province_rewards, List<NameAndEvidence> country_rewards, double volunteer, String volunteer_img, List<NameAndEvidence> self_qualifications) {
        this.fail = fail;
        this.report_cards = report_cards;
        this.school_rewards = school_rewards;
        this.city_rewards = city_rewards;
        this.province_rewards = province_rewards;
        this.country_rewards = country_rewards;
        this.volunteer = volunteer;
        this.volunteer_img = volunteer_img;
        this.self_qualifications = self_qualifications;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public List<String> getReport_cards() {
        return report_cards;
    }

    public void setReport_cards(List<String> report_cards) {
        this.report_cards = report_cards;
    }

    public List<NameAndEvidence> getSchool_rewards() {
        return school_rewards;
    }

    public void setSchool_rewards(List<NameAndEvidence> school_rewards) {
        this.school_rewards = school_rewards;
    }

    public List<NameAndEvidence> getCity_rewards() {
        return city_rewards;
    }

    public void setCity_rewards(List<NameAndEvidence> city_rewards) {
        this.city_rewards = city_rewards;
    }

    public List<NameAndEvidence> getProvince_rewards() {
        return province_rewards;
    }

    public void setProvince_rewards(List<NameAndEvidence> province_rewards) {
        this.province_rewards = province_rewards;
    }

    public List<NameAndEvidence> getCountry_rewards() {
        return country_rewards;
    }

    public void setCountry_rewards(List<NameAndEvidence> country_rewards) {
        this.country_rewards = country_rewards;
    }

    public double getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(double volunteer) {
        this.volunteer = volunteer;
    }

    public String getVolunteer_img() {
        return volunteer_img;
    }

    public void setVolunteer_img(String volunteer_img) {
        this.volunteer_img = volunteer_img;
    }

    public List<NameAndEvidence> getSelf_qualifications() {
        return self_qualifications;
    }

    public void setSelf_qualifications(List<NameAndEvidence> self_qualifications) {
        this.self_qualifications = self_qualifications;
    }
}
