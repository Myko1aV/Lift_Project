package lift;

import java.io.*;
import java.util.Properties;

public class Config {
    static File file = new File("./src/main/resources/file.config");

    public static int getFontSize() {
        return Integer.parseInt(getConfig("Font_Size"));
    }

    public static int getLiftMaxWeight() {
        return Integer.parseInt(getConfig("Lift_max_weight"));
    }

    public static int getGetLiftMaxCount() {
        return Integer.parseInt(getConfig("Lift_max_user_count"));
    }

    private static String getConfig(String key) {
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            Properties properties = new Properties();
            properties.load(reader);

            var retValue = properties.getProperty(key);
            System.out.println(retValue);

            return retValue;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
