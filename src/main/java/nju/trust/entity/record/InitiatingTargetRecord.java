package nju.trust.entity.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InitiatingTargetRecord extends BaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Long targetId;

    @Override
    public String toString() {
        return "InitiatingTargetRecord{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", username='" + username + '\'' +
                ", time=" + time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
