package com.logzc.webzic.orm.dao;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/22.
 */
public interface Dao<T,ID extends Serializable> {

    /**
     * Save or update.
     * @param entity to be saved.
     * @return Number of rows updated in database. Here must be 1.
     */
    int save(T entity) throws SQLException;


    /**
     * Delete an item.
     * @param entity item to be deleted.
     * @return rows affected. Here must be 1.
     */
    int delete(T entity) throws SQLException;

}
