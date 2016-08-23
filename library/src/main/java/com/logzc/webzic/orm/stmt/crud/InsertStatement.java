package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * Use this class to insert one row.
 * <p>
 * Created by lishuang on 2016/8/23.
 */
public class InsertStatement<T, ID> extends BaseStatement<T, ID> {

    protected InsertStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        super(tableInfo, statement, argColumnTypes);
    }

    /**
     * INSERT INTO `accounts` (`name` ,`password` ) VALUES (?,?);
     */
    public static <T, ID> InsertStatement<T, ID> build(DbType dbType, TableInfo<T, ID> tableInfo) throws SQLException {

        ColumnType[] columnTypes = tableInfo.getColumnTypes();

        if (columnTypes == null || columnTypes.length == 0) {
            throw new SQLException("No column available in " + tableInfo.getTableClass());
        }


        StringBuilder sb = new StringBuilder("INSERT INTO `" + tableInfo.getTableName() + "` (");

        boolean first=true;

        for (ColumnType columnType:columnTypes){
            if(first){
                first=false;
            }else{
                sb.append(',');
            }
            sb.append("`").append(columnType.getName()).append("`");
        }

        sb.append(") VALUES (");

        first=true;
        for (ColumnType ignored :columnTypes){
            if(first){
                first=false;
            }else{
                sb.append(',');
            }
            sb.append("?");
        }
        sb.append(");");

        return new InsertStatement<T, ID>(tableInfo, sb.toString(), columnTypes);
    }

}
