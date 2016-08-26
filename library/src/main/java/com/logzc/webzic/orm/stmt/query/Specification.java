package com.logzc.webzic.orm.stmt.query;

import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * This is a special class contains all the query conditions.
 * Created by lishuang on 2016/8/26.
 */
public interface Specification<T> {

    Predicate getPredicate(TableInfo<T, ?> tableInfo, CriteriaBuilder cb) throws SQLException;

}
