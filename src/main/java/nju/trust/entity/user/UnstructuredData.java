package nju.trust.entity.user;

import javax.persistence.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/28
 * Description:
 */
@Entity
public class UnstructuredData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 审核的图片
     */
    @Lob
    private String evidence;

    private Double score;

    private String description;

    @Enumerated
    private UnstructuredDataType dataType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnstructuredDataType getDataType() {
        return dataType;
    }

    public void setDataType(UnstructuredDataType dataType) {
        this.dataType = dataType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
