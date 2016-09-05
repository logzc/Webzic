package com.logzc.webzic.orm.stmt.crud;

import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Sort;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

/**
 * query statement.
 * Created by lishuang on 2016/8/23.
 */
public class QueryStatement<T, ID> extends BaseStatement<T, ID> {
    protected CriteriaBuilder criteriaBuilder;



    private QueryStatement(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) {
        super(connectionSource, tableInfo);

        this.criteriaBuilder = new CriteriaBuilder();
    }

    public static <T, ID> QueryStatement<T, ID> build(ConnectionSource connectionSource, TableInfo<T, ID> tableInfo) throws SQLException {

        return new QueryStatement<>(connectionSource, tableInfo);
    }

    public String buildStatementByPredicate(Predicate predicate) {
        String statement = null;

        if (predicate == null) {


            statement = dialect.getSqlSelectFrom();
            statement = MessageFormat.format(statement, tableInfo.getTableName());

        } else {

            String whereClause = predicate.getStatement(dialect);
            statement = dialect.getSqlSelectFromWhere();
            statement = MessageFormat.format(statement, tableInfo.getTableName(), whereClause);
        }

        return statement;
    }
    public String buildStatementByPredicate(Predicate predicate,Sort sort) {
        String statement = null;

        if (predicate == null) {

            if(sort==null){
                statement = dialect.getSqlSelectFrom();
                statement = MessageFormat.format(statement, tableInfo.getTableName());
            }else{
                statement = dialect.getSqlSelectFromOrderBy();
                statement = MessageFormat.format(statement, tableInfo.getTableName(),sort.getStatement(connectionSource.getDialect()));
            }



        } else {
            String whereClause = predicate.getStatement(dialect);
            if(sort==null){
                statement = dialect.getSqlSelectFromWhere();
                statement = MessageFormat.format(statement, tableInfo.getTableName(), whereClause);
            }else{
                statement = dialect.getSqlSelectFromWhereOrderBy();
                statement = MessageFormat.format(statement, tableInfo.getTableName(),whereClause,sort.getStatement(connectionSource.getDialect()));
            }

        }

        return statement;
    }


    public String buildStatement(Specification<T> specification) throws SQLException {

        return buildStatement(specification,null);
    }
    public String buildStatement(Specification<T> specification, Sort sort) throws SQLException {

        Predicate predicate=null;
        if(specification!=null){
            predicate = specification.getPredicate(this.tableInfo, this.criteriaBuilder);
        }

        return buildStatementByPredicate(predicate,sort);
    }



    public Object[] extractArgsFromPredicate(Predicate predicate) throws SQLException {
        Object[] args = null;
        if (predicate == null) {
            args = new Object[0];
        } else {

            List<Object> argsList = predicate.getArgs();

            args = new Object[argsList.size()];
            for (int i = 0; i < args.length; i++) {
                args[i] = argsList.get(i);
            }

        }
        return args;
    }

    public Object[] extractArgs(Specification<T> specification) throws SQLException {

        if(specification==null){
            return new Object[0];
        }

        Predicate predicate = specification.getPredicate(this.tableInfo, this.criteriaBuilder);

        return extractArgsFromPredicate(predicate);
    }
}
