package nju.trust.entity.user;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class IntermediateUser extends PrimaryUser {

    private String studentId;

    private String major;

    private Integer grade;

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

    @Override
    public String toString() {
        return "IntermediateUser{" +
                "studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                '}' + super.toString();
    }
}
