package nju.trust.web.user;

import nju.trust.util.APIContext;
import nju.trust.util.CitiAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/25
 * @Todo: 与CitiApi 相关，
 */
@RestController
@RequestMapping("/citi")
public class CitiAccountController {

    @Autowired
    APIContext apiContext;

    @RequestMapping("/confirm")
    public void confirm() {
        try{
            CitiAccountHelper.transferConfirm(apiContext);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @RequestMapping("/basic")
    public String basic() {
        String name = "";
        try{
            name = CitiAccountHelper.getName(apiContext);
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }


}
