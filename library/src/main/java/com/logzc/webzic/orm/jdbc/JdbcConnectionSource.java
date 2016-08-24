package com.logzc.webzic.orm.jdbc;

import com.logzc.webzic.orm.db.DbConnection;
import com.logzc.webzic.orm.db.DbType;
import com.logzc.webzic.orm.db.DbTypeUtils;
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
    private DbType dbType;
    private DbConnection dbConnection;
    public JdbcConnectionSource(String url) throws SQLException{
        this.url=url;
        //initialize.
        this.dbType= DbTypeUtils.forUrl(url);
        this.dbType.setDriver(DriverManager.getDriver(url));

    }





    @Override
    public DbType getDbType() {
        return dbType;
    }

    @Override
    public DbConnection getDbConnection() throws SQLException {

        if(this.dbConnection!=null){

            return this.dbConnection;
        }else{
            this.dbConnection=makeConnection();
        }


        return this.dbConnection;
    }


    protected DbConnection makeConnection() throws SQLException{

        Connection connection=DriverManager.getConnection(url);

        connection.setAutoCommit(true);

        DbConnection dbConnection=new JdbcDbConnection(connection);

        System.out.println("");

        return dbConnection;

    }

    @Override
    public void close() throws IOException {

        if(this.dbConnection!=null){
            this.dbConnection.close();
            this.dbConnection=null;
        }

    }

}
