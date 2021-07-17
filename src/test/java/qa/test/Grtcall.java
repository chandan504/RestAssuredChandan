package qa.test;
   import io.restassured.config.LogConfig;
   import io.restassured.path.json.JsonPath;
   import io.restassured.response.Response;
   import static io.restassured.RestAssured.*;
   import static io.restassured.matcher.RestAssuredMatchers.*;
   import static org.hamcrest.MatcherAssert.assertThat;
   import static org.hamcrest.Matchers.*;
public class Grtcall {


@org.testng.annotations.Test
public void test(){
 Response res= given().
          baseUri("https://api.postman.com").
          header("x-api-key","PMAK-60ba0fad49bfcd00588968e8-85bb9e8b65c307fe40d7b8904b2e3c4c6c")
           .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                 when().
                 get("/workspaces").
          then().
                 assertThat().
                 statusCode(200).extract().response();
    assertThat(res.path("workspaces[0].name"), equalTo("ChandanRest"));
  //  JsonPath resss= new JsonPath(res.asString());

//            body("workspaces.name",hasItems("ChandanRest","My Workspace","Team Workspace")).
//                 log().all();

//    System.out.println("name is "+ res.path("workspaces[0].name"));
 //   System.out.println("name is "+ resss.getString("workspaces[0].name"));
    System.out.println(JsonPath.from(res.asString()).getString("workspaces[0].name"));


}

}
