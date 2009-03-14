package hoi.addrbook.ui;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class UINamesCtrl {
    private static Properties names = new Properties();
    static {
        try {
            names.loadFromXML(UINamesCtrl.class.getResourceAsStream("localize.xml"));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLocalName(String name) {
        return names.getProperty(name, name);
    }
}
