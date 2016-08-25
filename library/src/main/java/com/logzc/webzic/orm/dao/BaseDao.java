package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.result.EntityMapper;
import com.logzc.webzic.orm.result.Mapper;
import com.logzc.webzic.orm.stmt.crud.DeleteStatement;
import com.logzc.webzic.orm.stmt.crud.InsertStatement;
import com.logzc.webzic.orm.stmt.crud.QueryStatement;
import com.logzc.webzic.orm.stmt.crud.UpdateStatement;
import com.logzc.webzic.orm.stmt.query.Criteria;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class BaseDao<T, ID> implements Dao<T, ID> {


    protected DbType dbType;
    protected final Class<T> dataClass;
    protected ConnectionSource connectionSource;
    protected TableInfo<T, ID> tableInfo;

    protected InsertStatement<T, ID> insertStatement;
    protected DeleteStatement<T, ID> deleteStatement;
    protected UpdateStatement<T, ID> updateStatement;
    protected QueryStatement<T, ID> queryStatement;


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


        this.deleteStatement = DeleteStatement.build(this.tableInfo);

        this.updateStatement = UpdateStatement.build(this.tableInfo);

        this.queryStatement = QueryStatement.build(this.tableInfo);

        this.mapper = new EntityMapper<>();
    }

    @Override
    public int insert(T entity) throws SQLException {

        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.insertStatement.getStatement();
        Object[] args = tableInfo.getColumnValues(entity);

        return dbConnection.insert(statement, args);
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

        return dbConnection.delete(statement, args);

    }

    @Override
    public int update(T entity) throws SQLException {
        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.updateStatement.getStatement();


        Object[] args = tableInfo.getColumnValues(entity);
        int length = args.length;
        Object[] allArgs = new Object[length + 1];
        System.arraycopy(args, 0, allArgs, 0, length);
        allArgs[length] = tableInfo.getIdValue(entity);


        return dbConnection.update(statement, allArgs);
    }


    @Override
    public T findOne(ID id) throws SQLException {

        DbConnection dbConnection = connectionSource.getDbConnection();

        String statement = this.queryStatement.getStatement();
        Object[] args = new Object[]{id};

        try (DbResults dbResults = dbConnection.query(statement, args)) {

            return dbResults.getEntity(tableInfo);

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<T> findAll() throws SQLException {

        String statement = this.queryStatement.getQueryAllStatement();
        Object[] args = new Object[]{};

        return queryRaw(statement, args);
    }




    @Override
    public List<T> query(Criteria criteria) throws SQLException {

        //String statement = "SELECT * FROM `{0}` {1} ;";
        //statement = MessageFormat.format(statement, tableInfo.getTableName(), tableInfo.getIdColumnType().getName());
        //String statement="select * from ``";

        return null;
    }

    @Override
    public List<T> queryRaw(String statement, Object... args) throws SQLException {
        DbConnection dbConnection = connectionSource.getDbConnection();

        try (DbResults dbResults = dbConnection.query(statement, args)) {
            return dbResults.getEntityList(tableInfo);
        } catch (IOException e) {
            throw new SQLException(e);
        }

    }

    public static <T, ID> Dao<T, ID> createDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException {
        return new BaseDao<>(connectionSource, clazz);
    }
}
