package nju.trust.entity.user;

import javafx.util.converter.LocalDateStringConverter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
@Entity
public class CreditCrossCheck {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = User.class)
    private User relatedPerson;

    //应该结算的时间
    private LocalDate endDate;

    private int q1;

    private int q2;

    private int q3;

    private int q4;

    private int q5;

    private int q6;

    private int q7;

    private int q8;

    private int q9;

    private boolean done = false;

    private boolean random = true;

    private boolean valid = true;

    public CreditCrossCheck() {

    }

    public CreditCrossCheck(User user, User relatedPerson, LocalDate endDate, boolean random) {
        this.user = user;
        this.relatedPerson = relatedPerson;
        this.endDate = endDate;
        this.random = random;
    }

    public CreditCrossCheck(User user, User relatedPerson, LocalDate endDate, int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9, boolean done, boolean random, boolean valid) {
        this.user = user;
        this.relatedPerson = relatedPerson;
        this.endDate = endDate;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
        this.q9 = q9;
        this.done = done;
        this.random = random;
        this.valid = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRelatedPerson() {
        return relatedPerson;
    }

    public void setRelatedPerson(User relatedPerson) {
        this.relatedPerson = relatedPerson;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }

    public int getQ6() {
        return q6;
    }

    public void setQ6(int q6) {
        this.q6 = q6;
    }

    public int getQ7() {
        return q7;
    }

    public void setQ7(int q7) {
        this.q7 = q7;
    }

    public int getQ8() {
        return q8;
    }

    public void setQ8(int q8) {
        this.q8 = q8;
    }

    public int getQ9() {
        return q9;
    }

    public void setQ9(int q9) {
        this.q9 = q9;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
