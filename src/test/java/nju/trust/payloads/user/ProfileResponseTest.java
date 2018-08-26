package nju.trust.payloads.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: J.D. Liao
 * Date: 2018/8/14
 */
@JsonTest
@RunWith(SpringRunner.class)
public class ProfileResponseTest {

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test() throws Exception {
        String userInfo = "{\n" +
                "    \"username\": \"str\",\n" +
                "    \"name\": \"str\",\n" +
                "    \"money\": 0.5,\n" +
                "    \"collectedMoney\": 0.5,\n" +
                "    \"grantedMoney\": 0.5}";

        System.out.println(mapper.readValue(userInfo, ProfileResponse.class));
    }


}