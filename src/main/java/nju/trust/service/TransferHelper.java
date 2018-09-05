package nju.trust.service;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: J.D. Liao
 * Date: 2018/9/5
 * Description:
 */
@Component
public class TransferHelper {

    private UserRepository userRepository;

    @Autowired
    public TransferHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void transferMoneyToUser(String username, Double money) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.addAccount(money);
        userRepository.save(user);
    }
}
