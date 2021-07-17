package OAUth.util;

import java.util.Properties;

public class configloader {
    private final Properties properties;
    private static configloader configloader;

    public configloader(){
        properties= propertyutils.propertyloder("src/test/resources/cofig.properties");
    }
    public static configloader loader(){

        if (configloader== null) {
            configloader = new configloader();
        }
       return configloader;

    }
public String getClientID(){
        String prop= properties.getProperty("client_id");
        if (prop !=null)   return prop;
           else throw new RuntimeException("Erron on property file to load clientid ");
}
    public String getClientsecret(){
        String prop= properties.getProperty("client_secret");
        if (prop !=null)   return prop;
         else    throw new RuntimeException("Erron on property file to load clientid ");
    }

    public String getgrant_type(){
        String prop= properties.getProperty("grant_type");
        if (prop !=null)   return prop;
          else   throw new RuntimeException("Erron on property file to load clientid ");

    }
    public String getrefresh_token(){
        String prop= properties.getProperty("refresh_token");
        if (prop !=null)   return prop;
        else     throw new RuntimeException("Erron on property file to load clientid ");

    }
    public String user_id(){
        String prop= properties.getProperty("user_id");
        if (prop !=null)   return prop;
            throw new RuntimeException("Erron on property file to load clientid ");
    }
    public String getPLaylistid(){
        String prop= properties.getProperty("playlist_id");
        if (prop !=null)
            return prop;
        throw new RuntimeException("Erron on property file to load clientid ");
    }
    public String getUpdatedPlaylist(){
        String prop= properties.getProperty("Update_playlist");
        if (prop !=null)
            return prop;
        throw new RuntimeException("Erron on property file to load clientid ");
    }
}
