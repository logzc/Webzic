package com.logzc.webzic.orm.stmt;

import com.logzc.webzic.orm.dao.BaseDao;
import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.table.TableInfo;

/**
 * Created by lishuang on 2016/8/23.
 */
public class StatementExecutor<T, ID> {
    private final DbType dbType;
    private final TableInfo<T, ID> tableInfo;
    private final Dao<T, ID> dao;

    public StatementExecutor(DbType dbType, TableInfo<T, ID> tableInfo, Dao<T, ID> dao) {
        this.dbType=dbType;
        this.tableInfo=tableInfo;
        this.dao=dao;

    }
}
