package com.xiejh.cn.system;

import java.io.*;
import java.util.Properties;

/**
 * Author xiejh
 * Date   2019/4/15 17:12
 **/
public class ApplicationConfig {

    private static ApplicationConfig INSTANCE = new ApplicationConfig();

    private Properties properties;

    private final static String FILE_NAME = "application.properties";

    private ApplicationConfig() {
        initProperties();
    }

    public static ApplicationConfig getInstance() {
        return INSTANCE;
    }

    private void initProperties() {
        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath));
            InputStream is=this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
