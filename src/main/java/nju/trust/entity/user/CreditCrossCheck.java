package nju.trust.entity.user;

import javafx.util.converter.LocalDateStringConverter;

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

    public CreditCrossCheck() {

    }


}
