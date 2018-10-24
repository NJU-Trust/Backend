package nju.trust.entity.user;

import nju.trust.entity.CreditRating;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author: 许杨
 * @Description: 用户历史信用记录
 * @Date: 2018/10/24
 */
@Entity
public class CreditRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "username", nullable = false)
    private User user;                              // 用户

    private LocalDate date;                         // 时间

    private Double creditScore;                     // 信用分数

    @Enumerated(EnumType.STRING)
    private CreditRating creditRating;              // 信用评级

    public CreditRecord() {}

    public CreditRecord(User user, LocalDate date, Double creditScore, CreditRating creditRating) {
        this.user = user;
        this.date = date;
        this.creditScore = creditScore;
        this.creditRating = creditRating;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }
}
