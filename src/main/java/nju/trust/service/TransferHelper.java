package nju.trust.service;

import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.util.APIContext;
import nju.trust.util.CitiAccountHelper;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Author: J.D. Liao
 * Date: 2018/9/5
 * Description:
 */

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/14
 * @Todo: add api and revise bugs
 */

@Component
public class TransferHelper {

    private static final Logger log = LoggerFactory.getLogger("TransferHelper");

    private UserRepository userRepository;

    @Autowired
    private Base64 base64;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private APIContext apiContext;


    @Autowired
    public TransferHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public void transferLoanToUserAccount(User user, Double money) {
        user.addAccount(money);
        transferMoney(money);
        userRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean getRepaymentFromUserAccount(User user, Double money) {
        if (user.hasEnoughMoney(money)) {
            user.minusAccount(money);
            //todo Add money to company account
            transferMoney(money);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void repaidToInvestor(User borrower, User investor, Double money) {
        // todo use citi-api to do transfering work and add a record
        transferMoney(money);
    }

    private void transferMoney(double money) {
        try{
            CitiAccountHelper.getAccounts(apiContext);
            CitiAccountHelper.getPayeeList(apiContext);
            CitiAccountHelper.getPersonalDestSrc(apiContext);
            CitiAccountHelper.transferMoney(apiContext, money);
            CitiAccountHelper.transferConfirm(apiContext);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
