package com.logzc.webzic.orm;

import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.support.ConnectionSource;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class OrmTest {

    @Test
    public void testOrm() throws Exception{
        String url="jdbc:mysql://localhost:3306/demo?user=root&password=lishmoshou511&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";

        //This can be ignored owing to spi.
        //Class.forName("com.mysql.cj.jdbc.Driver");

        try(ConnectionSource connectionSource=new JdbcConnectionSource(url)){


            Dao<Account,String> accountDao = DaoManager.createDao(connectionSource,Account.class);

            Account account0=accountDao.findOne("Lucy");

            System.out.println(account0.getName()+"->"+account0.getPassword());

            System.out.println("---------------------");

            List<Account> accounts=accountDao.findAll();
            for (Account account:accounts){
                System.out.println(account.getName()+"->"+account.getPassword());
            }
        }

    }
}
