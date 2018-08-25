package nju.trust.entity.user;

import nju.trust.entity.CreditRating;
import nju.trust.entity.SchoolType;
import nju.trust.entity.UserLevel;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.target.BaseTarget;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String platformAdvice;

    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    private CreditRating creditRating;

    private String roles;

    private String briefIntro;

    private String phoneNumber;

    @Email
    private String email;

    /**
     * Complete user's attributes
     */
    private String realName;

    private String idCardNumber;

    /**
     * Intermediate user's attributes
     */
    private String studentId;

    private String major;

    private Integer grade;

    private String stuCardImage;

    /**
     * School fellows attributes
     */
    @ElementCollection(targetClass = String.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> accomplishment;

    private String message;

    private String diplomaId;

    private SchoolType schoolType;

    /**
     * 学习成绩排名率
     */
    @DecimalMax("1.0")
    @DecimalMin("0.0")
    private Double rankingRate;

    /**
     * 挂科数
     */
    @Min(0)
    private Integer failedSubjects;

    /**
     * 获奖情况
     */
    @ElementCollection
    @Basic(fetch = FetchType.LAZY)
    private Map<AwardLevel, Integer> awards;

    /**
     * 违约记录
     */
    @ElementCollection
    @Basic(fetch = FetchType.LAZY)
    private Map<DefaultType, Integer> defaultRecords;
    /**
     * 学生工作类型
     */
    private StudentWorkType studentWorkType;

    /**
     * 平均每年志愿时长
     */
    private Double averagedVolunteerTime;

    /**
     * 学历情况
     */
    private EducationLevel educationLevel;

    @OneToMany(mappedBy = "user")
    private List<BaseTarget> publishedTargets;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", platformAdvice='" + platformAdvice + '\'' +
                ", userLevel=" + userLevel +
                ", creditRating=" + creditRating +
                ", roles='" + roles + '\'' +
                ", briefIntro='" + briefIntro + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                ", stuCardImage='" + stuCardImage + '\'' +
                ", accomplishment=" + accomplishment +
                ", message='" + message + '\'' +
                ", diplomaId='" + diplomaId + '\'' +
                ", schoolType=" + schoolType +
                ", rankingRate=" + rankingRate +
                ", failedSubjects=" + failedSubjects +
                ", awards=" + awards +
                ", defaultRecords=" + defaultRecords +
                ", studentWorkType=" + studentWorkType +
                ", averagedVolunteerTime=" + averagedVolunteerTime +
                ", educationLevel=" + educationLevel +
                '}';
    }

    public List<BaseTarget> getPublishedTargets() {
        return publishedTargets;
    }

    public void setPublishedTargets(List<BaseTarget> publishedTargets) {
        this.publishedTargets = publishedTargets;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
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

    public Map<AwardLevel, Integer> getAwards() {
        return awards;
    }

    public void setAwards(Map<AwardLevel, Integer> awards) {
        this.awards = awards;
    }

    public Map<DefaultType, Integer> getDefaultRecords() {
        return defaultRecords;
    }

    public void setDefaultRecords(Map<DefaultType, Integer> defaultRecords) {
        this.defaultRecords = defaultRecords;
    }

    public StudentWorkType getStudentWorkType() {
        return studentWorkType;
    }

    public void setStudentWorkType(StudentWorkType studentWorkType) {
        this.studentWorkType = studentWorkType;
    }

    public Double getAveragedVolunteerTime() {
        return averagedVolunteerTime;
    }

    public void setAveragedVolunteerTime(Double averagedVolunteerTime) {
        this.averagedVolunteerTime = averagedVolunteerTime;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
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
}
