package com.logzc.webzic.orm;

import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.support.ConnectionSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

/**
 * test cases for orm.
 * Created by lishuang on 2016/8/23.
 */
public class OrmTest {

    ConnectionSource connectionSource;

    @Before
    public void init() throws Exception {
        String url = "jdbc:mysql://localhost:3306/demo?user=root&password=lishmoshou511&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        connectionSource = new JdbcConnectionSource(url);
    }

    @After
    public void destory() throws Exception {
        connectionSource.close();
    }


    @Test
    public void testDelete() throws Exception {
        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        int result =accountDao.deleteById("lishuang");

        assumeTrue(result==1);

    }

    @Test
    public void testInsert() throws Exception {
        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        Account account = new Account();
        account.setName("lishuang");
        account.setPassword("1");

        int result =accountDao.insert(account);

        assumeTrue(result==1);

    }

    @Test
    public void testFindOne() throws Exception {

        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        Account account = accountDao.findOne("Lucy");

        assumeNotNull(account);

        assumeTrue(account.getPassword().equals("111"));

        System.out.println(account.getName() + "->" + account.getPassword());


    }

    @Test
    public void testFindAll() throws Exception {


        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        List<Account> accounts = accountDao.findAll();

        assumeTrue(accounts.size() == 3);

        for (Account account : accounts) {
            System.out.println(account.getName() + "->" + account.getPassword());
        }
    }


}
