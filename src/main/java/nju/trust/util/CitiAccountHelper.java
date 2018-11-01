package nju.trust.util;

import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.UUID;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/10/30
 * @Todo:
 */
public class CitiAccountHelper {

    public static String getAccounts(APIContext context) throws IOException {
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + context.getRealAccessToken();
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println(responseBodyString);
        return responseBodyString;
    }


    public static String transferMoney(APIContext context, double money) throws IOException{
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"sourceAccountId\":\""+ APIConstant.sourceAccountID +"\",\"transactionAmount\":"+money + ",\"transferCurrencyIndicator\":\"SOURCE_ACCOUNT_CURRENCY\",\"payeeId\":\""+APIConstant.payeeID+"\",\"destinationAccountId\":\""+APIConstant.destinationAccountID+"\"  \"chargeBearer\":\"BENEFICIARY\",\"paymentMethod\":\"GIRO\",\"fxDealReferenceNumber\":\"12345678\",\"remarks\":\"lamigc\",\"transferPurpose\":\"CASH_DISBURSEMENT\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/personalDomesticTransfers/preprocess")
                .post(body)
                .addHeader("authorization", "REPLACE_THIS_VALUE")
                .addHeader("uuid", "REPLACE_THIS_VALUE")
                .addHeader("accept", "application/json")
                .addHeader("client_id", "REPLACE_THIS_VALUE")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String controlFlowId = (String) jsonObject.get("controlFlowId");
        context.setControlFlowId(controlFlowId);
        System.out.println(controlFlowId);
        return controlFlowId;
    }

    public static String getPersonalDestSrc(APIContext context) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + context.getRealAccessToken();
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/personalDomesticTransfers/destinationAccounts/sourceAccounts")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println(responseBodyString);
        return responseBodyString;
    }

    public static String getPayeeList(APIContext context) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + context.getRealAccessToken();
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/payees")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println(responseBodyString);

        return responseBodyString;

    }


    public static String transferConfirm(APIContext apiContext) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + apiContext.getRealAccessToken();
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, "{\"controlFlowId\":\""+ apiContext.getControlFlowId()+"\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/personalDomesticTransfers")
                .post(requestBody)
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();

        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println(responseBodyString);
        return responseBodyString;
    }

//    public static String getBasic(String realAccessToken,HttpSession session) throws IOException {
//        String client_id = APIConstant.CLIENT_ID;
//        String authorization = "Bearer " + realAccessToken;
//        UUID uuid = UUID.randomUUID();
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://sandbox.apihub.citi.com/gcb/api/v1/customers/profiles/basic")
//                .get()
//                .addHeader("authorization", authorization)
//                .addHeader("uuid", uuid.toString())
//                .addHeader("content-type", "application/json")
//                .addHeader("accept", "application/json")
//                .addHeader("client_id", client_id)
//                .build();
//        Response response = client.newCall(request).execute();
//        logger.info("get-basic-response: {}",JSON.toJSONString(response));
//        String responseBodyString = response.body().string();
//        AuthorizeResponse authorizeResponse = JSON.parseObject(responseBodyString,AuthorizeResponse.class);
//        logger.info("basic:{}",JSON.toJSONString(authorizeResponse));
//        if(authorizeResponse.getType()==null){
//            System.out.println("basic_info:");
//            System.out.println("\t"+responseBodyString);
//            CustomerBasic customerBasic = JSON.parseObject(responseBodyString,CustomerBasic.class);
//            CustomerParticular customerParticular = customerBasic.getCustomerParticulars();
//            String prefix = customerParticular.getPrefix().substring(0,1).toUpperCase()+customerParticular.getPrefix().toLowerCase().substring(1);
//            String lastName = customerParticular.getNames()[0].getLastName().substring(0,1).toUpperCase()+customerParticular.getNames()[0].getLastName().substring(1).toLowerCase();
//            String nickname = prefix+"."+lastName;
//            return nickname;
//        }else {
//            GetAuthorize.authorize(session,authorizeResponse);
//        }
//        return "";
//
//    }



}
