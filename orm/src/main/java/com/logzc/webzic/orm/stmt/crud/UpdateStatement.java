package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * update statement.
 * Created by lishuang on 2016/8/23.
 */
public class UpdateStatement<T, ID> extends BaseStatement<T, ID> {

    protected UpdateStatement(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) {
        super(connectionSource, tableInfo);

        this.statement = this.dialect.buildSqlUpdate(tableInfo.getTableName(), tableInfo.getColumnNames(), tableInfo.getIdColumnType().getName());
    }

    /**
     * UPDATE `accounts` SET `password` = ? WHERE `name` = ?;
     */
    public static <T, ID> UpdateStatement<T, ID> build(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) throws SQLException {

        ColumnType[] columnTypes = tableInfo.getColumnTypes();
        ColumnType idColumnType = tableInfo.getIdColumnType();

        if (idColumnType == null) {
            //throw new SQLException("Cannot update " + tableInfo.getTableClass() + " because it doesn't have an id field");
            return null;
        }

        if (columnTypes == null || columnTypes.length == 0) {
            throw new SQLException("No column available in " + tableInfo.getTableClass());
        }


        return new UpdateStatement<T, ID>(connectionSource, tableInfo);
    }

}
