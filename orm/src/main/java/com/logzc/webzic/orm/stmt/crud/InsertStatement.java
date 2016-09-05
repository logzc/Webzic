package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * Use this class to insert one row.
 * <p>
 * Created by lishuang on 2016/8/23.
 */
public class InsertStatement<T, ID> extends BaseStatement<T, ID> {


    protected InsertStatement(ConnectionSource connectionSource,TableInfo<T, ID> tableInfo) throws SQLException {
        super(connectionSource,tableInfo);

        ColumnType[] columnTypes = tableInfo.getColumnTypes();

        if (columnTypes == null || columnTypes.length == 0) {
            throw new SQLException("No column available in " + tableInfo.getTableClass());
        }

        this.statement=this.dialect.buildSqlInsert(tableInfo.getTableName(),tableInfo.getColumnNames());

    }

    /**
     * INSERT INTO `accounts` (`name` ,`password` ) VALUES (?,?);
     */
    public static <T, ID> InsertStatement<T, ID> build(ConnectionSource connectionSource,TableInfo<T, ID> tableInfo) throws SQLException {



        return new InsertStatement<>(connectionSource,tableInfo);
    }



}
