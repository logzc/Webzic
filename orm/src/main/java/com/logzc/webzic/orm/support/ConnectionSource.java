package com.logzc.webzic.orm.support;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.dialect.Dialect;

import java.io.Closeable;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface ConnectionSource extends Closeable {


    Dialect getDialect();

    DbConnection getDbConnection() throws SQLException;



}
