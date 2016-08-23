package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * query statement.
 * Created by lishuang on 2016/8/23.
 */
public class QueryStatement<T, ID> extends BaseStatement<T, ID> {

    protected QueryStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        super(tableInfo, statement, argColumnTypes);
    }


    //TODO: The query clause needs to be finished.
    public static <T, ID> QueryStatement<T, ID> build(DbType dbType, TableInfo<T, ID> tableInfo, String statement) throws SQLException {

        return new QueryStatement<>(tableInfo, statement, tableInfo.getColumnTypes());
    }

}
