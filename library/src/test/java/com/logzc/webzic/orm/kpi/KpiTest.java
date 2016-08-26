package com.logzc.webzic.orm.kpi;

import com.logzc.webzic.orm.Account;
import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.support.ConnectionSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assume.assumeTrue;

/**
 * Created by lishuang on 2016/8/26.
 */
public class KpiTest {

    ConnectionSource connectionSource;

    @Before
    public void init() throws Exception {

        try {
            //PhoenixDriver ph=new PhoenixDriver();
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        String url = "jdbc:phoenix:9.181.86.37:2181:/hbase";
        connectionSource = new JdbcConnectionSource(url, "root", "passw0rd");
    }

    @After
    public void destory() throws Exception {
        connectionSource.close();
    }


    @Test
    public void testUtility() throws SQLException{

        Dao<KpiHistoryByUtility, ?> utilityDao = DaoManager.createDao(connectionSource, KpiHistoryByUtility.class);

        List<KpiHistoryByUtility> list=utilityDao.findAll();

        assumeTrue(list.size() == 2);



    }
}
