package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.result.EntityMapper;
import com.logzc.webzic.orm.result.Mapper;
import com.logzc.webzic.orm.stmt.crud.DeleteStatement;
import com.logzc.webzic.orm.stmt.crud.InsertStatement;
import com.logzc.webzic.orm.stmt.crud.QueryStatement;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class BaseDao<T, ID> implements Dao<T, ID> {


    protected DbType dbType;
    protected final Class<T> dataClass;
    protected ConnectionSource connectionSource;
    protected TableInfo<T, ID> tableInfo;
    protected QueryStatement<T, ID> queryStatement;
    protected InsertStatement<T, ID> insertStatement;
    protected DeleteStatement<T, ID> deleteStatement;


    //convert DbResults to Entity.
    protected Mapper<T> mapper;


    public BaseDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {


        this.connectionSource = connectionSource;
        this.dataClass = dataClass;

        init();
    }


    public void init() throws SQLException {

        this.dbType = connectionSource.getDbType();

        this.tableInfo = new TableInfo<T, ID>(connectionSource, this, dataClass);


        this.insertStatement = InsertStatement.build(this.tableInfo);

        this.queryStatement = QueryStatement.build(this.tableInfo);

        this.deleteStatement = DeleteStatement.build(this.tableInfo);

        this.mapper = new EntityMapper<>();
    }

    @Override
    public int insert(T entity) throws SQLException {

        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.insertStatement.getStatement();
        Object[] args = tableInfo.getColumnValues(entity);
        ColumnType[] columnTypes = tableInfo.getColumnTypes();

        return dbConnection.insert(statement, args, columnTypes);
    }


    @Override
    public int delete(T entity) throws SQLException {


        return 0;
    }

    @Override
    public int deleteById(ID id) throws SQLException {

        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.deleteStatement.getStatement();
        Object[] args = new Object[]{id};
        ColumnType[] columnTypes = new ColumnType[]{tableInfo.getIdColumnType()};

        return dbConnection.delete(statement, args, columnTypes);

    }

    @Override
    public int update(T entity) throws SQLException {
        return 0;
    }


    @Override
    public T findOne(ID id) throws SQLException {

        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.queryStatement.getStatement();
        Object[] args = new Object[]{id};
        ColumnType[] columnTypes = new ColumnType[]{tableInfo.getIdColumnType()};

        try (DbResults dbResults = dbConnection.query(statement, args, columnTypes)) {

            return dbResults.getEntity(tableInfo);

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.queryStatement.getQueryAllStatement();
        Object[] args = new Object[]{};
        ColumnType[] columnTypes = new ColumnType[]{};

        try (DbResults dbResults = dbConnection.query(statement, args, columnTypes)) {
            return dbResults.getEntityList(tableInfo);
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }


    public static <T, ID> Dao<T, ID> createDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException {
        return new BaseDao<>(connectionSource, clazz);
    }
}
