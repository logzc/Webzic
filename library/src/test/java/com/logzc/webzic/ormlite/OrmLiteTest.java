package com.logzc.webzic.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mysql.jdbc.Driver;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/22.
 */
public class OrmLiteTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testRrmLite() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        // this uses h2 by default but change to match your database
        //String databaseUrl = "jdbc:h2:mem:account";
        String databaseUrl = "jdbc:mysql://localhost:3306/demo?user=root&password=lishmoshou511";
        // create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);
        // instantiate the dao
        Dao<Account, String> accountDao =
                DaoManager.createDao(connectionSource, Account.class);
        // if you need to create the 'accounts' table make this call
        TableUtils.createTable(connectionSource, Account.class);
        //Once we have con gured our database objects, we can use them to persist an Account to the database and query for it from the database by its ID:
        // create an instance of Account
        Account account = new Account();
        account.setName("Jim Coakley");
        // persist the account object to the database
        accountDao.create(account);
        // retrieve the account from the database by its id field (name)
        Account account2 = accountDao.queryForId("Jim Coakley");
        System.out.println("Account: " + account2.getName());
        // close the connection source
        connectionSource.close();
    }
}
