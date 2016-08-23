package com.logzc.webzic.orm.support;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbType;

import java.io.Closeable;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface ConnectionSource extends Closeable {


    DbType getDbType();

    DbConnection getDbConnection() throws SQLException;

}
