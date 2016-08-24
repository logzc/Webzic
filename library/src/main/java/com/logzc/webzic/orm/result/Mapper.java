package com.logzc.webzic.orm.result;

import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/24.
 */
public interface Mapper<T> {

    /**
     * Convert the DbResult to an instance.
     */
    T map(DbResults results, T entity, ColumnType[] columnTypes) throws SQLException;

}
