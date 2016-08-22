package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.db.DbTypeUtils;
import com.logzc.webzic.orm.support.ConnectionSource;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcConnectionSource implements ConnectionSource {

    private String url;
    private DbType dbType;
    public JdbcConnectionSource(String url) throws SQLException{
        this.url=url;

        this.dbType= DbTypeUtils.forUrl(url);
        this.dbType.setDriver(DriverManager.getDriver(url));

    }

    public void initialize() throws SQLException{



    }

    @Override
    public void close() throws IOException {

    }
}
