package nju.trust.entity.user;

import nju.trust.entity.CreditRating;
import nju.trust.entity.UserLevel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: J.D. Liao
 * Date: 2018/8/9
 */
@Entity
public class User {

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String avatar;

    private String platformAdvice;

    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    private CreditRating creditRating;

    private String roles;

    private String phoneNumber;

    private String email;

    private String realName;

    private String idCardNumber;

    private String studentId;

    private String major;

    private Integer grade;

    private String briefIntro;

    @ElementCollection(targetClass = String.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> accomplishment;

    private String message;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", platformAdvice='" + platformAdvice + '\'' +
                ", userLevel=" + userLevel +
                ", creditRating=" + creditRating +
                ", roles='" + roles + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                ", briefIntro='" + briefIntro + '\'' +
                ", accomplishment=" + accomplishment +
                ", message='" + message + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPlatformAdvice() {
        return platformAdvice;
    }

    public void setPlatformAdvice(String platformAdvice) {
        this.platformAdvice = platformAdvice;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public List<String> getAccomplishment() {
        return accomplishment;
    }

    public void setAccomplishment(List<String> accomplishment) {
        this.accomplishment = accomplishment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<RoleName> getRoles() {
        if (roles == null) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableSet(
                    new HashSet<>(Arrays.stream(roles.split(","))
                            .map(RoleName::valueOf).collect(Collectors.toSet())));
        }
    }

    public void setRoles(ArrayList<RoleName> role) {
        if (role == null) {
            roles = null;
        } else {
            roles = "";
            for (int i = 0; i < role.size(); i++) {
                roles += role.get(i).name();
                if (i != role.size() - 1) {
                    roles += ",";
                }
            }
        }
    }
}
