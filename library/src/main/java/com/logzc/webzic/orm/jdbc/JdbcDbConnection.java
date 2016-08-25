package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.util.OrmUtils;

import java.io.IOException;
import java.sql.*;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcDbConnection implements DbConnection {

    private Connection connection;

    public JdbcDbConnection(Connection connection) {
        this.connection = connection;

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
            System.out.println(stmt);

            return stmt.executeUpdate();
        }

    }

    @Override
    public DbResults query(String statement, Object... args) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        setStatementArgs(stmt, args);

        //print the sql.
        System.out.println(stmt);

        ResultSet resultSet = stmt.executeQuery();
        System.out.println(resultSet);
        return new DbResults(stmt, resultSet);
    }




    private void setStatementArgs(PreparedStatement stmt, Object... args) throws SQLException {

        if (args == null) {
            return;
        }

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];

            if (arg == null) {
                stmt.setNull(i + 1, Types.NULL);
            } else {
                int sqlType = OrmUtils.getSqlType(arg.getClass());
                stmt.setObject(i + 1, arg, sqlType);
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
