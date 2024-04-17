package utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class Utility {
    public static String readThePropertyFile(String key) {
        String returningValue = null;
        try {
            PropertiesConfiguration prop = new PropertiesConfiguration("./src/test/resources/config.properties");

            returningValue = (String) prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return returningValue;
    }

    public static void writeIntoPropertyFile(String key, String value) {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
            config.setProperty(key, value);
            config.save();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}
