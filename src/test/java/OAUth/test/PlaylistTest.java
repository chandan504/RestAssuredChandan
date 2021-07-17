package OAUth.test;

import OAUth.API.Statuscode;
import OAUth.API.applicationAPI.playListApi;
import OAUth.pojo.Error;
import OAUth.pojo.Playlist;
import OAUth.util.configloader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static OAUth.util.FakerUtils.generateDescription;
import static OAUth.util.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0 ")
@Feature("Playlist API ")
public class PlaylistTest {

@Step
    public Playlist playlistBuilder(String name,String des, boolean Public){

        return Playlist.builder()
                .name(name)
                .description(des)
                ._public(Public)
                .build();

    }
    @Step
public void assertplaylist(Playlist responsePlaylist, Playlist requestPlaylist){
    assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()) );
    assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()) );
    assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));

    }
    @Step
public void assertStatus(int actualstatus, int expectedstatuscode){
    assertThat(actualstatus,equalTo(expectedstatuscode));
}
@Step
public void assertError(Error responceError, int expectedstatuscode, String msg){
    assertThat(responceError.getError().getStatus(),equalTo(expectedstatuscode));
    assertThat(responceError.getError().getMessage(),equalTo(msg));


}


    @Story("Create a Playlist")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("989")
    @Description("This test case is responsible to check the playlist should created")
    @Test(description = "should able to create playlist")
    public void createPlaylist(){
       Playlist  requestPlaylist= playlistBuilder(generateName(),generateDescription(),false);
       Response response= playListApi. post(requestPlaylist);
       assertStatus(response.statusCode(), Statuscode.CODE_201.getcode());
       assertplaylist(response.as(Playlist.class),requestPlaylist);
    }



    @Test
    public void getPlaylist(){
        Playlist  requestPlaylist= playlistBuilder("My new Playlist","Updated playlist description",false);
        Response res= playListApi.get(configloader.loader().getPLaylistid());
        assertStatus(res.statusCode(),Statuscode.CODE_200.getcode());
        assertplaylist(res.as(Playlist.class),requestPlaylist);
    }

    @Test
    public void updatePlaylist(){
        Playlist  requestPlaylist= playlistBuilder(generateName(),generateDescription(),false);
        Response res= playListApi.update(configloader.loader().getUpdatedPlaylist(), requestPlaylist);
        assertStatus(res.statusCode(),Statuscode.CODE_200.getcode());
    }
    @Test
    @Story("Create a Playlist")
    public void playListwithoutname(){
        Playlist  requestPlaylist= playlistBuilder("",generateDescription(),false);
        Response res= playListApi.post( requestPlaylist);
        assertStatus(res.statusCode(),Statuscode.CODE_400.getcode());
        assertError(res.as(Error.class),Statuscode.CODE_400.getcode(),Statuscode.CODE_400.getDescription());

    }

    @Test
    @Story("Create a Playlist")
    public void playListWithExpairedToken(){
        Playlist  requestPlaylist= playlistBuilder(generateName(),generateDescription(),false);
        Response res= playListApi.post( "12345",requestPlaylist);
        assertStatus(res.getStatusCode(),Statuscode.CODE_401.getcode());
        assertError(res.as(Error.class),Statuscode.CODE_401.getcode(),Statuscode.CODE_401.getDescription());
        }
}
