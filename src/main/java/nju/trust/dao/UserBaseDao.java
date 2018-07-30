package nju.trust.dao;

import nju.trust.entity.user.PrimaryUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserBaseDao extends CrudRepository<PrimaryUser, String> {
    Optional<PrimaryUser> findByUsername(String username);
}