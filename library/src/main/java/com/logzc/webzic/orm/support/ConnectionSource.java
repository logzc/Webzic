package com.logzc.webzic.orm.support;

import com.logzc.webzic.orm.db.DbType;

import java.io.Closeable;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface ConnectionSource extends Closeable {


    DbType getDbType();
}
