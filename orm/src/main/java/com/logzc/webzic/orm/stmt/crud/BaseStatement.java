package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

/**
 * Created by lishuang on 2016/8/23.
 */
public abstract class BaseStatement<T, ID> {

    protected final ConnectionSource connectionSource;
    protected final TableInfo<T, ID> tableInfo;
    protected final Class<T> clazz;
    protected final ColumnType idColumnType;
    protected String statement;

    protected Dialect dialect;

    protected BaseStatement(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) {
        this.connectionSource=connectionSource;
        this.tableInfo = tableInfo;
        this.clazz = tableInfo.getTableClass();
        this.idColumnType = tableInfo.getIdColumnType();
        this.dialect=connectionSource.getDialect();
    }
    public String getStatement(){
        return this.statement;
    }

}
