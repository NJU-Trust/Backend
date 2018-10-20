package nju.trust.entity.user;

import javax.persistence.*;

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
