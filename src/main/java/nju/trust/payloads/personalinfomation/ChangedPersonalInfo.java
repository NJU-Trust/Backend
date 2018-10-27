package nju.trust.payloads.personalinfomation;

import nju.trust.entity.user.Gender;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/9/16
 */
public class ChangedPersonalInfo {

    private String username;

    private String gender;

    private Integer age;

    private String institution;

    private String livingPlace;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }
}
