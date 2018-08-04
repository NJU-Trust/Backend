package nju.trust.entity.user;

import nju.trust.entity.CreditRating;
import nju.trust.entity.UserLevel;
import org.apache.tomcat.util.buf.StringUtils;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@MappedSuperclass
public class BaseUser {

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String avatar;

    private String platformAdvice;

    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Enumerated(EnumType.STRING)
    private CreditRating creditRating;

    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPlatformAdvice() {
        return platformAdvice;
    }

    public void setPlatformAdvice(String platformAdvice) {
        this.platformAdvice = platformAdvice;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public Set<RoleName> getRoles() {
        if(roles == null) {
            return Collections.emptySet();
        }else{
            return Collections.unmodifiableSet(
                    new HashSet<RoleName>(Arrays.asList(roles.split(",")).stream().map(str -> RoleName.valueOf(str)).collect(Collectors.toSet()))
            );
        }
    }

    public void setRoles(ArrayList<RoleName> role) {
        if(role == null) {
            roles = null;
        }else {
            roles = "";
            for (int i = 0; i < role.size(); i++) {
                roles += role.get(i).name();
            }
        }
    }
}
