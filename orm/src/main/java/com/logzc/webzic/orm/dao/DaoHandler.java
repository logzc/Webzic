package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lishuang on 2016/10/12.
 */
public class DaoHandler<T, ID> extends BaseDao<T, ID> implements InvocationHandler {



    public DaoHandler(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public Object objectByAnd(Method method, Object[] args) {

        String methodName = method.getName();
        String pattern = "^queryBy(.+)$";
        String glue = "And";


        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(methodName);
        if (m.find()) {

            String clause = m.group(1);
            String[] columns=clause.split(glue);

            if(columns.length!=args.length){
                return null;
            }


            //TODO: write the specification here.
            /*
            Specification<T> specification=new Specification<T>() {
                @Override
                public Predicate getPredicate(TableInfo<T, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                    Class<?>[] clas
                    Class<?> clazz = method.getParameterTypes()[0];

                    Predicate predicate=cb.eq(tableInfo.getColumnType(columns[0]),args[0]);
                    Predicate agePredicate = cb.gt(tableInfo.getColumnType("age"), 3);
                    Predicate weightPredicate = cb.lt(tableInfo.getColumnType("weight"), 6);

                    return cb.and(agePredicate,weightPredicate);
                }
            }
            */

            return null;


        } else {

            return null;
        }


    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        try {

            Object result = method.invoke(this, args);

            return result;
        } catch (Exception e) {

            //Try to match queryByXXXAndXXX queryByXXXOrXXX


            return null;

        }


    }
}
