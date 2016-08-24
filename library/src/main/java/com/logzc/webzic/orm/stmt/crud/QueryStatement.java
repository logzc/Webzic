package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * query statement.
 * Created by lishuang on 2016/8/23.
 */
public class QueryStatement<T, ID> extends BaseStatement<T, ID> {

    protected QueryStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        super(tableInfo, statement, argColumnTypes);
    }


    //TODO: The query clause needs to be finished.
    public static <T, ID> QueryStatement<T, ID> build(DbType dbType, TableInfo<T, ID> tableInfo) throws SQLException {

        String statement = "SELECT * FROM `{0}` WHERE `{1}` = ? ;";
        statement = MessageFormat.format(statement, tableInfo.getTableName(), tableInfo.getIdColumnType().getName());

        return new QueryStatement<>(tableInfo, statement, tableInfo.getColumnTypes());
    }



}
