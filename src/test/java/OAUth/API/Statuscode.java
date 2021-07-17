package OAUth.API;


public enum Statuscode {

    CODE_200(200,""),
    CODE_201(201, ""),
    CODE_400(400,"Missing required field: name"),
    CODE_401(401,"Invalid access token");

    private  final int  code;
    private final String msg;

    Statuscode(int CODE, String MSG) {
        this.code=CODE;
        this.msg= MSG;
    }
    public int getcode(){
    return code;
    }
    public String getDescription(){
    return msg;
    }
}
