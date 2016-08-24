package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.table.TableInfo;

/**
 * Created by lishuang on 2016/8/23.
 */
public abstract class BaseStatement<T, ID> {

    protected final TableInfo<T, ID> tableInfo;
    protected final Class<T> clazz;
    protected final ColumnType idColumnType;
    protected final String statement;
    protected final ColumnType[] argColumnTypes;

    protected BaseStatement(TableInfo<T, ID> tableInfo, String statement, ColumnType[] argColumnTypes) {
        this.tableInfo = tableInfo;
        this.clazz = tableInfo.getTableClass();
        this.idColumnType = tableInfo.getIdColumnType();
        this.statement = statement;
        this.argColumnTypes = argColumnTypes;
    }

    public String getStatement() {
        return statement;
    }
}
