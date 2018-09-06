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

    public void transferLoanToUserAccount(User user, Double money) {
        user.addAccount(money);
        userRepository.save(user);
    }

    public boolean getRepaymentFromUserAccount(User user, Double money) {
        if (user.hasEnoughMoney(money)) {
            user.minusAccount(money);
            //todo Add money to company account
            userRepository.save(user);
            return true;
        } else
            return false;
    }

    public void repaidToInvestor(User borrower, User investor, Double money) {
        // todo use citi-api to do transfering work and add a record
    }
}
