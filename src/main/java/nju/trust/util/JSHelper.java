package nju.trust.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.script.*;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/31
 * @Todo:
 */
public class JSHelper {
    public static String RSAConverter(String modulus,String exponent,String eventId,String password) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        Bindings bindings = engine.createBindings();
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        Resource resource = new ClassPathResource("CitiE2E.js");
        // 获得js文件
        try {
            engine.eval(new InputStreamReader(resource.getInputStream()));
            Invocable invocable = (Invocable) engine;
            JSMethod executeMethod = invocable.getInterface(JSMethod.class);
            return executeMethod.getRSAPassword(modulus, exponent, eventId, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }


}
