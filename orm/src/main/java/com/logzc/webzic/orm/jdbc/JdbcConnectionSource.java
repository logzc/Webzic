package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.dialect.Dialect;
import com.logzc.webzic.orm.dialect.DialectManager;
import com.logzc.webzic.orm.support.ConnectionSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class JdbcConnectionSource implements ConnectionSource {

    private String url;
    private Dialect dialect;
    private DbConnection dbConnection;

    private String user;
    private String password;

    public JdbcConnectionSource(Connection connection, Dialect dialect) throws SQLException {

        this.dbConnection = new JdbcDbConnection(connection, this);
        this.dialect = dialect;

    }


    public JdbcConnectionSource(String url) throws SQLException {
        this(url, null, null);

    }

    public JdbcConnectionSource(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        //initialize.
        this.dialect = DialectManager.forUrl(url);
        this.dialect.setDriver(DriverManager.getDriver(url));

    }


    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DbConnection getDbConnection() throws SQLException {

        if (this.dbConnection != null) {

            return this.dbConnection;
        } else {
            this.dbConnection = makeConnection();
        }


        return this.dbConnection;
    }


    protected DbConnection makeConnection() throws SQLException {
        Connection connection;
        if (user != null && password != null) {
            connection = DriverManager.getConnection(url, user, password);
        } else {
            connection = DriverManager.getConnection(url);
        }


        connection.setAutoCommit(true);


        return new JdbcDbConnection(connection, this);

    }

    @Override
    public void close() throws IOException {

        if (this.dbConnection != null) {
            this.dbConnection.close();
            this.dbConnection = null;
        }

    }

}
