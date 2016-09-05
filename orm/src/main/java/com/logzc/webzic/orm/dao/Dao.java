package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.stmt.query.Sort;
import com.logzc.webzic.orm.stmt.query.Specification;

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

    T queryOne(ID id) throws SQLException;

    T queryOne(Specification<T> specification) throws SQLException;
    T queryOne(Specification<T> specification, Sort sort) throws SQLException;

    List<T> queryAll() throws SQLException;
    ;
    List<T> query(Specification<T> specification) throws SQLException;
    List<T> query(Specification<T> specification, Sort sort) throws SQLException;


    List<T> queryRaw(String statement, Object... args) throws SQLException;

    T queryOneRaw(String statement, Object... args) throws SQLException;


}
