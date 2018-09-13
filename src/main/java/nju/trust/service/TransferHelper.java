package nju.trust.service;

import com.google.gson.JsonParser;
import io.jsonwebtoken.impl.Base64Codec;
import jdk.nashorn.internal.parser.JSONParser;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.user.User;
import nju.trust.exception.ResourceNotFoundException;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Author: J.D. Liao
 * Date: 2018/9/5
 * Description:
 */

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/12
 * @Todo: add api
 */

@Component
public class TransferHelper {

    private static final Logger log = LoggerFactory.getLogger("PathUtils");

    private String client_id = "550778b2-5ac4-476c-ac5e-526050ec6c50";

    private String client_secret = "V1uT8jD0yQ0qA4iV1cO7jB7dB1xU0kI6hH4kV1uX6bB5vC5vV4";

    private String access_token;

    private UserRepository userRepository;

    @Autowired
    private Base64 base64;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    public TransferHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void transferLoanToUserAccount(User user, Double money) {
        transferMoney();
        user.addAccount(money);
        userRepository.save(user);
    }

    @Transactional
    public boolean getRepaymentFromUserAccount(User user, Double money) {
        if (user.hasEnoughMoney(money)) {
            user.minusAccount(money);
            //todo Add money to company account
            transferMoney();
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void repaidToInvestor(User borrower, User investor, Double money) {
        // todo use citi-api to do transfering work and add a record
        transferMoney();
    }

    private void transferMoney() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        JsonParser jsonParser = new JsonParser();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        try {
            Request request = new Request.Builder()
                    .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb")
                    .post(body)
                    .addHeader("accept", "application/json")
                    .addHeader("authorization", "Basic " + base64.encodeToString((client_id + ":" + client_secret).getBytes("UTF-8")))
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            access_token = jsonParser.parse(response.body().string()).getAsJsonObject().get("access_token").getAsString();

            body = RequestBody.create(mediaType, "{\"sourceAccountId\":\"3255613852316f2b4d4d796c344e38756339654972776f663745446e6d4c32486f455a4165374a476858343d\",\"transactionAmount\":4500.25,\"transferCurrencyIndicator\":\"SOURCE_ACCOUNT_CURRENCY\",\"payeeId\":\"C$0003019202$AU$XX$01000540000001\",\"chargeBearer\":\"BENEFICIARY\",\"paymentMethod\":\"GIRO\",\"fxDealReferenceNumber\":\"12345678\",\"remarks\":\"teduw\",\"transferPurpose\":\"CASH_DISBURSEMENT\"}");
            request = new Request.Builder()
                    .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess")
                    .post(body)
                    .addHeader("authorization", "Bearer " + access_token)
                    .addHeader("uuid", id)
                    .addHeader("accept", "application/json")
                    .addHeader("client_id", client_id)
                    .addHeader("content-type", "application/json")
                    .build();
            response = okHttpClient.newCall(request).execute();
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
