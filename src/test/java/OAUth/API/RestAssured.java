package OAUth.API;

import OAUth.pojo.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static OAUth.API.specBuilder.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class RestAssured {

    public  static  Response post(String path,String token, Object requestPlaylist) {
      return given(requestspec()).
            body(requestPlaylist).
              auth().oauth2(token).
            when().post(path).
            then()
              .spec(responsespec())
               .extract().response();
}

public static Response postAccount(HashMap<String ,String> formParms){
    return     given(requestAccountspec())
            .formParams(formParms)
            .when().post("/api/token")
            .then()
            .spec(responsespec()).extract().response();


}
public static Response get(String path,String token){
  return given(requestspec()).
          auth().oauth2(token).
            when().get(path).
            then()
            .spec(responsespec())
            .extract().response();
}

    public static Response update(String token,String path,Object requestPlaylist){
        return given(requestspec()).
                auth().oauth2(token).
                body(requestPlaylist).
                when().put(path).
                then()
                .spec(responsespec())
                .extract().response();
    }
}
