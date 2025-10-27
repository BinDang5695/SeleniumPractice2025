package settings.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "configs" + File.separator + "pexels.properties";

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        files.add("src" + File.separator + "test" + File.separator + "resources" + File.separator + "configs" + File.separator + "pexels.properties");
//        files.add("src" + File.separator + "test" + File.separator + "resources" + File.separator + "configs" + File.separator + "pixabay.properties");
//        files.add("src/test/resources/configs/production.properties");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    public static Properties loadByEnvironment(String env) {
        String filePath = "src/test/resources/configs/" + env + ".properties";
        try {
            properties = new Properties();
            linkFile = SystemHelper.getCurrentDir() + File.separator + filePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String value = null;
        try {
            if (file == null) {
                setDefaultFile();
            }

            value = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public static void setValue(String key, String keyValue) {
        try {
            if (file == null) {
                setDefaultFile();
                out = new FileOutputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
            }

            out = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setValue(String key, String keyValue, String filePath) {
        Properties properties = new Properties();

        try {
            if (file == null) {
                setDefaultFile();
                out = new FileOutputStream(SystemHelper.getCurrentDir() + filePath);
            }

            out = new FileOutputStream(SystemHelper.getCurrentDir() + filePath);
            System.out.println(SystemHelper.getCurrentDir() + filePath);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}