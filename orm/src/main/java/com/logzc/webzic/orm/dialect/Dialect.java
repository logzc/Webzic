package com.logzc.webzic.orm.dialect;

import java.sql.Driver;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface Dialect {

    //check whether the url match this Dialect.
    boolean isUrlMatch(String url);


    void setDriver(Driver driver);


    String getSqlColumn(String nameName);

    String buildSqlInsert(String tableName, String[] columnNames);

    String buildSqlUpdate(String tableName, String[] columnNames, String idColumnName);



    String getSqlSelectFrom();

    String getSqlSelectFromOrderBy();

    String getSqlSelectFromWhere();
    String getSqlSelectFromWhereOrderBy();


    String buildSqlDelete(String tableName, String idColumnName);

    Object getSqlObject(Object arg);

    int getSqlType(Object arg) throws SQLException;
}
