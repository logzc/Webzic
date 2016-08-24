package com.logzc.webzic.orm.result;

import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.field.ColumnType;

import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/24.
 */
public class EntityMapper<T> implements Mapper<T> {

    @Override
    public T map(DbResults results, T entity, ColumnType[] columnTypes) throws SQLException {

        for (ColumnType columnType : columnTypes) {

            Object val = results.getVal(columnType);

            columnType.assign(entity, val);
        }

        return entity;
    }


}
