package com.logzc.webzic.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lishuang on 2016/7/7.
 */
public final class PropertyUtil {


    /**
     *
     * @param filePath /com/webzic/config.properties or /config.properties
     */
    public static Properties loadProperties(String filePath){
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyUtil.class.getResourceAsStream(filePath);){

            properties.load(inputStream);

        } catch (Exception e) {
            return new Properties();
        }
        return properties;

    }



    public static String getProperty(String filePath,String key){

        Properties properties=loadProperties(filePath);

        return properties.getProperty(key);

    }

}
