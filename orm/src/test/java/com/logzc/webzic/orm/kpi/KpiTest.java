package com.logzc.webzic.orm.kpi;

import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.dialect.phoenix.function.ToDateFunction;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.kpi.model.KpiHistoryByFeeder;
import com.logzc.webzic.orm.kpi.model.KpiHistoryByUtility;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;
import com.logzc.webzic.orm.util.OrmUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
    public void testFeeder() throws SQLException {

        final String utility = "dte";
        final String substationRegion = "Substation_COMLK";
        String feeder = "COMLK9020";

        final String range = "selected";
        String from = "08/10/2016";
        String to = "09/22/2016";

        final Date date1 = OrmUtils.toDate(from, "MM/dd/yyyy");
        final Date date2 = OrmUtils.toDate(to, "MM/dd/yyyy");


        Dao<KpiHistoryByFeeder, ?> dao = DaoManager.createDao(connectionSource, KpiHistoryByFeeder.class);

        List<KpiHistoryByFeeder> list = dao.query(new Specification<KpiHistoryByFeeder>() {
            @Override
            public Predicate getPredicate(TableInfo<KpiHistoryByFeeder, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                Predicate fromPredicate = cb.gt(tableInfo.getColumnType("date"), new ToDateFunction(date1));
                Predicate toPredicate = cb.lt(tableInfo.getColumnType("date"), new ToDateFunction(date2));

                Predicate predicate = cb.and(fromPredicate, toPredicate);
                //Predicate predicate = fromPredicate;

                if ("selected".equals(range)) {
                    predicate = cb.and(predicate, cb.eq(tableInfo.getColumnType("substation_region"), substationRegion));
                }

                return predicate;
            }
        });

        System.out.println(list.size());


        TableInfo<KpiHistoryByFeeder, ?> feederTableInfo = new TableInfo<>(KpiHistoryByFeeder.class);
        for (KpiHistoryByFeeder item : list) {

            Object[] objects = feederTableInfo.getColumnValues(item);

            for (Object obj : objects) {
                System.out.print(obj + " ");
            }
            System.out.println("");

        }


    }


    @Test
    public void testUtility() throws SQLException {

        final String utility = "dte";
        String substationRegion = "Substation_COMLK";
        String feeder = "COMLK9020";

        final String range = "selected";
        String from = "08/10/2016";
        String to = "09/22/2016";

        final Date date1 = OrmUtils.toDate(from, "MM/dd/yyyy");
        final Date date2 = OrmUtils.toDate(to, "MM/dd/yyyy");


        Dao<KpiHistoryByUtility, ?> dao = DaoManager.createDao(connectionSource, KpiHistoryByUtility.class);
        List<KpiHistoryByUtility> list = dao.query(new Specification<KpiHistoryByUtility>() {
            @Override
            public Predicate getPredicate(TableInfo<KpiHistoryByUtility, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                Predicate fromPredicate = cb.gt(tableInfo.getColumnType("date"), new ToDateFunction(date1));
                Predicate toPredicate = cb.lt(tableInfo.getColumnType("date"), new ToDateFunction(date2));

                Predicate predicate = cb.and(fromPredicate, toPredicate);

                if ("selected".equals(range)) {
                    predicate = cb.and(predicate, cb.eq(tableInfo.getColumnType("utility"), utility));
                }

                return predicate;
            }
        });

        System.out.println(list.size());


        TableInfo<KpiHistoryByUtility, ?> feederTableInfo = new TableInfo<>(KpiHistoryByUtility.class);
        for (KpiHistoryByUtility item : list) {

            Object[] objects = feederTableInfo.getColumnValues(item);

            for (Object obj : objects) {
                System.out.print(obj + " ");
            }
            System.out.println("");

        }

    }




}
