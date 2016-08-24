package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbResults;
import com.logzc.webzic.orm.field.ColumnType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcDbConnection implements DbConnection {

    private Connection connection;

    public JdbcDbConnection(Connection connection) {
        this.connection = connection;

    }

    @Override
    public int insert(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException {
        return 0;
    }

    @Override
    public int update(String statement, Object[] args, ColumnType[] argColumnTypes) throws SQLException {
        return 0;
    }

    @Override
    public DbResults query(String statement, Object[] args, ColumnType[] columnTypes) throws SQLException {

        PreparedStatement stmt = this.connection.prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        setStatementArgs(stmt, args, columnTypes);

        //print the sql.
        System.out.println(stmt);

        ResultSet resultSet = stmt.executeQuery();
        System.out.println(resultSet);
        return new DbResults(stmt, resultSet);
    }




    private void setStatementArgs(PreparedStatement stmt, Object[] args, ColumnType[] columnTypes) throws SQLException {

        if (args == null) {
            return;
        }

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];

            int sqlType = columnTypes[i].getSqlType();
            if (arg == null) {
                stmt.setNull(i + 1, sqlType);
            } else {
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
