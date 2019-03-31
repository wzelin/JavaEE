package com.xxx.javaee.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesUtil {

    public static final Properties p;

    static {
        p = new Properties();
        InputStream in  = PropertiesUtil.class
                .getClassLoader()
                .getResourceAsStream("application.properties");
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return p.getProperty(key);
    }

    public static Properties getProperties(){
        return p;
    }

}
