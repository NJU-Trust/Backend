package nju.trust.dao;

import nju.trust.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}