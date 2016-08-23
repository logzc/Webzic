package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.StatementExecutor;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class BaseDao<T,ID> implements Dao<T,ID> {


    protected DbType dbType;
    protected final Class<T> dataClass;
    protected ConnectionSource connectionSource;
    protected TableInfo<T, ID> tableInfo;
    protected StatementExecutor<T, ID> statementExecutor;


    public BaseDao(ConnectionSource connectionSource, Class<T> dataClass)  throws SQLException{


        this.connectionSource=connectionSource;
        this.dataClass=dataClass;

        init();
    }


    public void init() throws SQLException{

        this.dbType=connectionSource.getDbType();

        this.tableInfo=new TableInfo<T,ID>(connectionSource,this,dataClass);

        this.statementExecutor=new StatementExecutor<T,ID>(dbType,tableInfo,this);

    }

    @Override
    public int save(T entity) throws SQLException {


        return 0;
    }

    @Override
    public int delete(T entity) throws SQLException {


        return 0;
    }

    @Override
    public T findOne(ID jim) {


        return null;
    }

    public static <T,ID> Dao<T,ID> createDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException  {
        return new BaseDao<T, ID>(connectionSource,clazz);
    }
}
