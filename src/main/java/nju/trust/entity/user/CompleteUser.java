package nju.trust.entity.user;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CompleteUser extends IntermediateUser{

    private String realName;

    private String idCardNumber;

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

    @Override
    public String toString() {
        return "CompleteUser{" +
                "realName='" + realName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}' + super.toString();
    }
}
