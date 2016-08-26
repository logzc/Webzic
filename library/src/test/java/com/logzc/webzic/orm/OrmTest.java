package com.logzc.webzic.orm;

import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.support.ConnectionSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
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
    public void testInsert() throws Exception {
        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        Account account = new Account(200, "log", true, 18, "zhimakaimen", 70.2f, 179.5, new Date());

        int result = accountDao.insert(account);

        assumeTrue(result == 1);

    }


    @Test
    public void testDelete() throws Exception {
        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        int result = accountDao.deleteById(200);

        assumeTrue(result == 1);

    }


    @Test
    public void testUpdate() throws Exception {
        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        Account account = accountDao.findOne(200);

        assumeNotNull(account);

        account.setAge(200);
        account.setFoodName("apple");

        int result = accountDao.update(account);

        assumeTrue(result == 1);

    }


    @Test
    public void testFindOne() throws Exception {

        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        Account account = accountDao.findOne(200);

        assumeNotNull(account);

        //assumeTrue(account.getPassword().equals("111"));

        //System.out.println(account.getName() + "->" + account.getPassword());


    }


    @Test
    public void testFindAll() throws Exception {

        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        List<Account> accounts = accountDao.findAll();

        assumeTrue(accounts.size() == 6);

    }

    /**
     * Criteria sql query.
     */
    @Test
    public void testCriteriaQuery() throws Exception {
        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);


        List<Account> accounts = accountDao.query((tableInfo, cb) -> {

            Predicate agePredicate = cb.gt(tableInfo.getColumnType("age"), 3);
            Predicate weightPredicate = cb.lt(tableInfo.getColumnType("weight"), 6);

            return cb.or(agePredicate,weightPredicate);
        });

        assumeTrue(accounts.size() == 6);
    }


    /**
     * Raw sql query.
     */
    @Test
    public void testRawQuery() throws Exception {
        Dao<Account, Integer> accountDao = DaoManager.createDao(connectionSource, Account.class);

        String statement = "select * from accounts where `age` > ? ;";

        List<Account> accounts = accountDao.queryRaw(statement, 3);

        assumeTrue(accounts.size() == 3);
    }

}
