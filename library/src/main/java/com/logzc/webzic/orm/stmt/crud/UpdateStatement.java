package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * update statement.
 * Created by lishuang on 2016/8/23.
 */
public class UpdateStatement<T, ID> extends BaseStatement<T, ID> {

    protected UpdateStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        super(tableInfo, statement, argColumnTypes);
    }

    /**
     * UPDATE `accounts` SET `password` = ? WHERE `name` = ?;
     */
    public static <T, ID> UpdateStatement<T, ID> build(DbType dbType, TableInfo<T, ID> tableInfo) throws SQLException {

        ColumnType[] columnTypes = tableInfo.getColumnTypes();
        ColumnType idColumnType = tableInfo.getIdColumnType();

        if (idColumnType == null) {
            throw new SQLException("Cannot update " + tableInfo.getTableClass() + " because it doesn't have an id field");
        }

        if (columnTypes == null || columnTypes.length == 0) {
            throw new SQLException("No column available in " + tableInfo.getTableClass());
        }

        StringBuilder sb = new StringBuilder("UPDATE `" + tableInfo.getTableName() + "` SET ");

        boolean first = true;

        for (ColumnType columnType : columnTypes) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            sb.append("`").append(columnType.getName()).append("` = ? ");
        }

        sb.append("WHERE `").append(idColumnType.getName()).append("` = ? ;");


        return new UpdateStatement<T, ID>(tableInfo, sb.toString(), columnTypes);
    }

}
