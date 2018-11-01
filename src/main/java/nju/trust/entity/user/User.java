package nju.trust.entity.user;

import nju.trust.entity.CreditRating;
import nju.trust.entity.UserLevel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
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

    private String phoneNumber;

    @Email
    private String email;

    private String avatar;

    private String realName;

    private String idCardNumber;

    /**
     * 校园卡号
     */
    private String studentId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    private String platformAdvice;

    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    private CreditRating creditRating;

    private String roles;

    private String briefIntro;

    private String major;

    private Integer grade;

    private String stuCardImage;

    /**
     * School fellows attributes
     */
    @ElementCollection(targetClass = String.class)
    private List<String> accomplishment;

    private String message;

    private String diplomaId;

    /**
     * 学习成绩排名率
     */
    @DecimalMax("1.0")
    @DecimalMin("0.0")
    private Double rankingRate;

    /**
     * 信用分数
     */
    private Double creditScore;

    //信用交叉检验得分
    private Double crossScore = -1.0;
    /**
     * 挂科数
     */
    @Min(0)
    private Integer failedSubjects;

    /**
     * 账户
     */
    private Double account;

    private String alipay;

    private LocalDate birthday;

    private String university;
    //学院 or 工作单位
    private String institution;
    //校园卡照片
    private String schoolCardImage;

    private String livingPlace;

    // 校园表现
    private Double campusScore;

    private String alipayPath;

    private String cardPath;


    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }

    public void addAccount(Double add) {
        account += add;
    }

    public boolean hasEnoughMoney(Double minus) {
        return (account - minus) > 0;
    }

    public void minusAccount(Double minus) {
        account -= minus;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    public Double getRankingRate() {
        return rankingRate;
    }

    public void setRankingRate(Double rankingRate) {
        this.rankingRate = rankingRate;
    }

    public Integer getFailedSubjects() {
        return failedSubjects;
    }

    public void setFailedSubjects(Integer failedSubjects) {
        this.failedSubjects = failedSubjects;
    }

    public String getDiplomaId() {
        return diplomaId;
    }

    public void setDiplomaId(String diplomaId) {
        this.diplomaId = diplomaId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStuCardImage() {
        return stuCardImage;
    }

    public void setStuCardImage(String stuCardImage) {
        this.stuCardImage = stuCardImage;
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

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSchoolCardImage() {
        return schoolCardImage;
    }

    public void setSchoolCardImage(String schoolCardImage) {
        this.schoolCardImage = schoolCardImage;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setLivingPlace(String livingPlace) {
        this.livingPlace = livingPlace;
    }

    public Set<RoleName> getRoles() {
        if (roles == null) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableSet(Arrays.stream(roles.split(","))
                    .map(RoleName::valueOf).collect(Collectors.toSet()));
        }
    }


    public void setRoles(ArrayList<RoleName> role) {
        if (role == null) {
            roles = null;
        } else {
            StringBuilder roleStr = new StringBuilder();
            for (int i = 0; i < role.size(); i++) {
                roleStr.append(role.get(i).name());
                if (i != role.size() - 1) {
                    roleStr.append(",");
                }
            }
            roles = roleStr.toString();
        }
    }

    public Double getCrossScore() {
        return crossScore;
    }

    public void setCrossScore(Double crossScore) {
        this.crossScore = crossScore;
    }

    public Double getCampusScore() {
        return campusScore;
    }

    public void setCampusScore(Double campusScore) {
        this.campusScore = campusScore;
    }


    public String getAlipayPath() {
        return alipayPath;
    }

    public void setAlipayPath(String alipayPath) {
        this.alipayPath = alipayPath;
    }

    public String getCardPath() {
        return cardPath;
    }

    public void setCardPath(String cardPath) {
        this.cardPath = cardPath;
    }
}
