package com.logzc.webzic.orm.kpi;

import com.logzc.webzic.orm.dao.Dao;
import com.logzc.webzic.orm.dao.DaoManager;
import com.logzc.webzic.orm.dialect.phoenix.function.ToDateFunction;
import com.logzc.webzic.orm.jdbc.JdbcConnectionSource;
import com.logzc.webzic.orm.kpi.model.KpiHistoryByFeeder;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;
import com.logzc.webzic.orm.util.OrmUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by lishuang on 2016/8/31.
 */
public class DescriptionTest {

    @Test
    public void testOrm() throws Exception {

        //Load Driver and get the ConnectionSource.
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        String uri = "jdbc:phoenix:9.181.86.37:2181:/hbase";
        ConnectionSource connectionSource = new JdbcConnectionSource(uri, "root", "passw0rd");
        //Get the dao.
        Dao<KpiHistoryByFeeder, ?> dao = DaoManager.createDao(connectionSource, KpiHistoryByFeeder.class);
        //Query the result.
        List<KpiHistoryByFeeder> list = dao.query(new Specification<KpiHistoryByFeeder>() {
            @Override
            public Predicate getPredicate(TableInfo<KpiHistoryByFeeder, ?> tableInfo, CriteriaBuilder cb) throws SQLException {
                //Config the Predicate.
                final Date date1 = OrmUtils.toDate("08/10/2016", "MM/dd/yyyy");
                final Date date2 = OrmUtils.toDate("09/22/2016", "MM/dd/yyyy");
                Predicate fromPredicate = cb.gt(tableInfo.getColumnType("date"), new ToDateFunction(date1));
                Predicate toPredicate = cb.lt(tableInfo.getColumnType("date"), new ToDateFunction(date2));
                Predicate predicate = cb.and(fromPredicate, toPredicate);
                predicate = cb.and(predicate, cb.eq(tableInfo.getColumnType("substation_region"), "Substation_COMLK"));
                return predicate;
            }
        });
        //print the result.
        System.out.println(list.size());
        //close connection.
        connectionSource.close();
    }

}
