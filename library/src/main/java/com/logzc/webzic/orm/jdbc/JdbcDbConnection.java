package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.field.ColumnType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcDbConnection implements DbConnection {

    private final Connection connection;

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
    public Object queryOne(String statement, Object[] args, ColumnType[] columnTypes) throws SQLException {

        PreparedStatement stmt = this.connection.prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        try {
            setStatementArgs(stmt,args,columnTypes);

            ResultSet resultSet =  stmt.executeQuery();


            System.out.println(resultSet);
            return resultSet;
        }finally {
            stmt.close();
        }



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
}
