package nju.trust.util;

import nju.trust.exception.CitiAuthorizationException;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.UUID;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/28
 * @Todo:
 */

public class CitiAuthorizationHelper {
    public static void getRealAccessToken(String username, String password, APIContext context){
        context.setUsername(username);
        context.setPassword(password);
        try {
            getAccessToken(context);
            if (context.getAccessToken() == null) {
                throw new CitiAuthorizationException();
            }
            getBiztoken(context);
            if (context.getEventId() == null) {
                throw new CitiAuthorizationException();
            }
            realAccessToken(context);
            if (context.getRealAccessToken() == null){
                throw new CitiAuthorizationException();
            }
            System.out.println(CitiAccountHelper.getName(context));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getAccessToken(APIContext context) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", authorization)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String accessToken = (String) jsonObject.get("access_token");
        context.setAccessToken(accessToken);
        System.out.println("access_token:");
        System.out.println("\t" + accessToken);
        return accessToken;
    }

    public static void getBiztoken(APIContext context) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String client_id = APIConstant.CLIENT_ID;
        String accessToken = context.getAccessToken();
        String authorization = "Bearer " + accessToken;
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/security/e2eKey")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("client_id", client_id)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String modulus = null;
        String exponent = null;
        String bizToken = null;
        String eventId = null;
        if (jsonObject != null) {
            modulus = (String) jsonObject.get("modulus");
            exponent = (String) jsonObject.get("exponent");
            Headers headers = response.headers();
            bizToken = headers.get("bizToken");
            eventId = headers.get("eventId");
            context.setEventId(eventId);
            context.setBizToken(bizToken);
            context.setExponent(exponent);
            context.setModulus(modulus);
        }
    }

    public static String realAccessToken(APIContext context) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        String bizToken = context.getBizToken();
        System.err.println("bizToken: "+bizToken);
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        String username = context.getUsername();
        String password = context.getPassword();
        System.out.println("password : " + password);
        password = JSHelper.RSAConverter(context.getModulus(), context.getExponent(), context.getEventId(), context.getPassword());
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=password&scope=/api&username="+username+"&password="+password);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("bizToken", bizToken)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String realAccessToken = (String) jsonObject.get("access_token");
        context.setRealAccessToken(realAccessToken);
        System.out.println("real_access_token:");
        System.out.println("\t" + realAccessToken);
        return realAccessToken;
    }


}
