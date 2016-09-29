package com.logzc.webzic.web.config;

import com.logzc.webzic.util.PropertyUtil;

import java.util.Properties;

/**
 * the constants in the webzic framework.
 * Created by lishuang on 2016/9/28.
 */
public class Constants {

    private Properties properties;
    private String scanPackage;

    private String getProperty(String key) {

        if (properties == null) {
            properties = PropertyUtil.loadProperties("/config.properties");
        }

        return properties.getProperty(key);
    }


    public String getScanPackage() {
        if (scanPackage == null) {

            scanPackage = getProperty("webzic.scan.package");

        }
        return scanPackage;
    }


}
