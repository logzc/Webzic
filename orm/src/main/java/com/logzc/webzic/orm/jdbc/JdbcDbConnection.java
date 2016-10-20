package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.util.OrmUtils;

import java.io.IOException;
import java.sql.*;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcDbConnection implements DbConnection {

    private Connection connection;
    private ConnectionSource connectionSource;

    public JdbcDbConnection(Connection connection,ConnectionSource connectionSource) {
        this.connection = connection;
        this.connectionSource=connectionSource;

    }

    @Override
    public int insert(String statement, Object... args) throws SQLException {
        return execute(statement,args);
    }

    @Override
    public int delete(String statement, Object... args) throws SQLException {
        return execute(statement,args);
    }


    @Override
    public int update(String statement, Object... args) throws SQLException {
        return execute(statement,args);
    }


    public int execute(String statement, Object... args) throws SQLException{
        try(PreparedStatement stmt = this.connection.prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
            setStatementArgs(stmt, args);

            //print the sql.
            printSql(stmt,statement,args);

            return stmt.executeUpdate();
        }

    }

    @Override
    public DbResults query(String statement, Object... args) throws SQLException {




        PreparedStatement stmt = this.connection.prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        setStatementArgs(stmt, args);

        printSql(stmt,statement,args);


        ResultSet resultSet = stmt.executeQuery();
        return new DbResults(stmt, resultSet);
    }


    private void printSql(PreparedStatement stmt,String statement,Object[] args){
        //print the sql.
        System.out.println(stmt);
        System.out.println(statement);
        System.out.print("Args:");
        for (Object obj:args){
            System.out.print(" "+obj);
        }
        System.out.println();
    }


    private void setStatementArgs(PreparedStatement stmt, Object... args) throws SQLException {

        if (args == null) {
            return;
        }

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];

            Object sqlArg=connectionSource.getDialect().getSqlObject(arg);
            int sqlType=connectionSource.getDialect().getSqlType(arg);

            if (arg == null) {
                stmt.setNull(i + 1, Types.NULL);
            } else {

                stmt.setObject(i + 1, sqlArg, sqlType);
            }

        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new IOException("could not close SQL connection.");
        }

    }
}
