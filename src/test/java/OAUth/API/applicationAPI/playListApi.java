package OAUth.API.applicationAPI;

import OAUth.API.RestAssured;
import OAUth.pojo.Playlist;
import OAUth.util.configloader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static OAUth.API.Route.PLAYLIST;
import static OAUth.API.Route.USER;
import static OAUth.API.TokenManager.getToken;
import static OAUth.API.specBuilder.requestspec;


public class playListApi {

@Step
    public  static  Response post(Playlist requestPlaylist) {
        return RestAssured.post(USER+"/"+ configloader.loader().user_id() +PLAYLIST, getToken(),requestPlaylist);

}
    public  static  Response post(String token, Playlist requestPlaylist) {
        return RestAssured.post(USER+""+ configloader.loader().user_id()+PLAYLIST, token,requestPlaylist);
    }
public static Response get(String PlaylistId){
        return RestAssured.get(PLAYLIST+"/"+PlaylistId,getToken());

}

    public static Response update(String PlaylistId,Playlist requestPlaylist){
        return RestAssured.update(getToken(),PLAYLIST+"/"+PlaylistId, requestPlaylist);
    }
}
