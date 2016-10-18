package com.logzc.webzic.web.config;

import com.logzc.webzic.util.PropertyUtil;

import java.util.Properties;

/**
 * the constants in the webzic framework.
 *
 * Properties file name: /config.properties
 *
 * Scan package key: webzic.scan.package
 * JDBC keys:
 *            webzic.jdbc.driver
 *            webzic.jdbc.url
 *            webzic.jdbc.user
 *            webzic.jdbc.password
 *
 * Created by lishuang on 2016/9/28.
 */
public class Constants {

    private Properties properties;
    private String scanPackage;

    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;


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
    public String getJdbcDriver(){
        if(jdbcDriver==null){
            jdbcDriver=getProperty("webzic.jdbc.driver");
        }
        return jdbcDriver;
    }
    public String getJdbcUrl(){
        if(jdbcUrl==null){
            jdbcUrl=getProperty("webzic.jdbc.url");
        }
        return jdbcUrl;
    }

    public String getJdbcUser(){
        if(jdbcUser==null){
            jdbcUser=getProperty("webzic.jdbc.user");
        }
        return jdbcUser;
    }
    public String getJdbcPassword(){
        if(jdbcPassword==null){
            jdbcPassword=getProperty("webzic.jdbc.password");
        }
        return jdbcPassword;
    }

}
