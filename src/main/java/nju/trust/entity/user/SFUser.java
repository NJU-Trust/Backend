package nju.trust.entity.user;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SFUser extends CompleteUser {

    private String briefIntro;

    @ElementCollection(targetClass = String.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> accomplishment;

    private String message;

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
}
