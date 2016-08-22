package com.logzc.webzic.orm.db;

import java.sql.Driver;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface DbType {

    //check whether the url match this DbType.
    boolean isUrlMatch(String url);


    void setDriver(Driver driver);

}
