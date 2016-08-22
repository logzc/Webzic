package com.logzc.webzic.orm.db;

import java.sql.Driver;

/**
 * Created by lishuang on 2016/8/23.
 */
public class MysqlDbType implements DbType {

    Driver driver;

    @Override
    public boolean isUrlMatch(String url) {
        return url != null && url.startsWith("jdbc:mysql:");

    }

    @Override
    public void setDriver(Driver driver) {
        this.driver=driver;
    }
}
