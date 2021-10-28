package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigProperties {
    private final Properties properties;

    public ConfigProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (FileNotFoundException exception) {
            Logger.getLogger(getClass().getName()).log(Level.ALL, "Properties file not found! " + exception.getMessage());
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.ALL, "Cannot read configuration file! " + exception.getMessage());
        }
    }

    public String readProperty(String key) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Reading Property " + key);
        return properties.getProperty(key, "NOT_FOUND");
    }
}
