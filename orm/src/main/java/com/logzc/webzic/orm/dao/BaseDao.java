package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.result.EntityMapper;
import com.logzc.webzic.orm.result.Mapper;
import com.logzc.webzic.orm.stmt.crud.DeleteStatement;
import com.logzc.webzic.orm.stmt.crud.InsertStatement;
import com.logzc.webzic.orm.stmt.crud.QueryStatement;
import com.logzc.webzic.orm.stmt.crud.UpdateStatement;
import com.logzc.webzic.orm.stmt.query.ComparisonOperator;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Sort;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.stmt.query.predicate.ComparisonPredicate;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class BaseDao<T, ID> implements Dao<T, ID> {

    protected Dialect dialect;
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

        this.dialect = connectionSource.getDialect();

        this.tableInfo = new TableInfo<>(dataClass);


        this.insertStatement = InsertStatement.build(this.connectionSource, this.tableInfo);


        this.deleteStatement = DeleteStatement.build(this.connectionSource, this.tableInfo);

        this.updateStatement = UpdateStatement.build(this.connectionSource, this.tableInfo);

        this.queryStatement = QueryStatement.build(this.connectionSource, this.tableInfo);


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
    public T queryOne(ID id) throws SQLException {

        Predicate predicate;
        if(id instanceof String){
            predicate = new ComparisonPredicate(null, ComparisonOperator.EQ, tableInfo.getIdColumnType(), (String)id);

        }else if(id instanceof Number){
            predicate = new ComparisonPredicate(null, ComparisonOperator.EQ, tableInfo.getIdColumnType(), (Number) id);
        }else{
            throw new SQLException("Not support such type's id.");
        }

        String statement = this.queryStatement.buildStatementByPredicate(predicate);
        Object[] args = this.queryStatement.extractArgsFromPredicate(predicate);

        DbConnection dbConnection = connectionSource.getDbConnection();

        try (DbResults dbResults = dbConnection.query(statement, args)) {

            return dbResults.getEntity(tableInfo);

        } catch (IOException e) {
            throw new SQLException(e);
        }
    }


    @Override
    public T queryOne(Specification<T> specification) throws SQLException {
        return queryOne(specification,null);
    }

    @Override
    public T queryOne(Specification<T> specification, Sort sort) throws SQLException {

        String statement = this.queryStatement.buildStatement(specification,sort);
        Object[] args = this.queryStatement.extractArgs(specification);

        DbConnection dbConnection = connectionSource.getDbConnection();
        try (DbResults dbResults = dbConnection.query(statement, args)) {
            return dbResults.getEntity(tableInfo);
        } catch (IOException e) {
            throw new SQLException(e);
        }

    }

    @Override
    public List<T> queryAll() throws SQLException {

        String statement = this.queryStatement.buildStatementByPredicate(null);
        Object[] args = this.queryStatement.extractArgsFromPredicate(null);

        return queryRaw(statement, args);
    }


    @Override
    public List<T> query(Specification<T> specification) throws SQLException {

        return query(specification,null);
    }

    @Override
    public List<T> query(Specification<T> specification, Sort sort) throws SQLException {


        String statement = this.queryStatement.buildStatement(specification,sort);
        Object[] args = this.queryStatement.extractArgs(specification);

        DbConnection dbConnection = connectionSource.getDbConnection();
        try (DbResults dbResults = dbConnection.query(statement, args)) {
            return dbResults.getEntityList(tableInfo);
        } catch (IOException e) {
            throw new SQLException(e);
        }
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

    @Override
    public T queryOneRaw(String statement, Object... args) throws SQLException {
        DbConnection dbConnection = connectionSource.getDbConnection();

        try (DbResults dbResults = dbConnection.query(statement, args)) {
            return dbResults.getEntity(tableInfo);
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    public static <T, ID> Dao<T, ID> createDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException {
        return new BaseDao<>(connectionSource, clazz);
    }
}
