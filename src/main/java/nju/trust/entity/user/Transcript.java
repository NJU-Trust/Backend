package nju.trust.entity.user;

import javax.persistence.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/29
 * Description: 成绩单（借款成功后需要每学年开学一周内上交成绩单）
 */
@Entity
public class Transcript {

    @Id
    @GeneratedValue
    private Long id;

    private String transcript;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
