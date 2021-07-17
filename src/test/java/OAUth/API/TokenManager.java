package OAUth.API;

import OAUth.util.configloader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static OAUth.API.specBuilder.requestAccountspec;
import static OAUth.API.specBuilder.responsespec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class TokenManager {

   private static String access_token;
   private static Instant expiry_time;


    public synchronized static String getToken(){
        try
        {
         if(access_token== null || Instant.now().isAfter(expiry_time))
             {
             System.out.println("Token is renewed");
             Response response = renewToken();
             access_token = response.path("access_token");
             int expireduration = response.path("expires_in");
             expiry_time = Instant.now().plusSeconds(expireduration);
              }
         else
             {
             System.out.println("Token is not expaired and good to use");
             }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Fatal !! unable to renew the token");
        }
     return  access_token;
    }


    private static Response renewToken(){
        HashMap<String,String> formParms= new HashMap<>();
        formParms.put("client_id", configloader.loader().getClientID());
        formParms.put("client_secret",configloader.loader().getClientsecret());
        formParms.put("grant_type",configloader.loader().getgrant_type());
        formParms.put("refresh_token",configloader.loader().getrefresh_token());

      Response response=  RestAssured.postAccount(formParms);

    if (response.statusCode()!=200){
        throw new RuntimeException("Not executedd.");
    }
    return response;

    }
}
