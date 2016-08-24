package com.logzc.webzic.orm.db;

import com.logzc.webzic.orm.field.ColumnType;

import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface DbConnection {

    /**
     * insert.
     *
     * @param statement      sql
     * @param args           object arguments for SQL '?'
     * @param argColumnTypes Field types of the arguments.
     * @return num of rows affected.
     */
    int insert(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException;

    int delete(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException;

    int update(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException;


    DbResults queryOne(String statement, Object[] args, ColumnType[] columnTypes) throws SQLException;


}
