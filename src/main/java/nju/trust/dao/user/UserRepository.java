package nju.trust.dao.user;

import nju.trust.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByStudentId(String studentId);

    Optional<User> findByStudentId(String studentId);

    List<User> findAllByInstitution(String institution);
}