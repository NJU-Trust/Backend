package nju.trust.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/31
 * @Todo:
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CitiAuthorizationException extends RuntimeException{
    public CitiAuthorizationException(){
        super("CiTi验证未通过");
    }


    public CitiAuthorizationException(String message) {
        super(message);
    }
}
