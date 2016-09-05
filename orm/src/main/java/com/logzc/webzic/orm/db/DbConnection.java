package com.logzc.webzic.orm.db;

import java.io.Closeable;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public interface DbConnection extends Closeable {

    /**
     * insert.
     *
     * @param statement      sql
     * @param args           object arguments for SQL '?'
     * @return num of rows affected.
     */
    int insert(String statement, Object... args) throws SQLException;

    int delete(String statement, Object... args) throws SQLException;

    int update(String statement, Object... args) throws SQLException;


    int execute(String statement, Object... args) throws SQLException;



    DbResults query(String statement, Object... args) throws SQLException;


}
