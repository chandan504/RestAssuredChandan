package OAUth.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class propertyutils {



    public static Properties propertyloder(String filepath) {
        Properties property = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            try {
                property.load(reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load property");
            }

            } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(" Property File not found ");
            }
        return property;
    }
}