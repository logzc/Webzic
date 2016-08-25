package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.stmt.query.Criteria;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lishuang on 2016/8/22.
 */
public interface Dao<T, ID> {

    /**
     * Save or update.
     *
     * @param entity to be saved.
     * @return Number of rows updated in database. Here must be 1.
     */
    //int save(T entity) throws SQLException;

    int insert(T entity) throws SQLException;

    /**
     * Delete an item.
     *
     * @param entity item to be deleted.
     * @return rows affected. Here must be 1.
     */
    int delete(T entity) throws SQLException;

    int deleteById(ID id) throws SQLException;

    int update(T entity) throws SQLException;

    T findOne(ID id) throws SQLException;

    List<T> findAll() throws SQLException;


    List<T> query(Criteria criteria) throws SQLException;


    List<T> queryRaw(String statement, Object... args) throws SQLException;
}