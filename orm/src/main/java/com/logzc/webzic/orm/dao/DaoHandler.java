package com.logzc.webzic.orm.dao;

import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lishuang on 2016/10/12.
 */
public class DaoHandler<T, ID> extends BaseDao<T, ID> implements InvocationHandler {


    public DaoHandler(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public Specification<T> specificationByOr(Method method, Object[] args) {
        return null;
    }

    public Specification<T> specificationByAnd(Method method, Object[] args) throws SQLException {

        String methodName = method.getName();
        String pattern = "^queryBy(.+)$";
        String glue = "And";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(methodName);
        if (m.find()) {

            String clause = m.group(1);
            String[] columns = clause.split(glue);

            if (columns.length != args.length || columns.length == 0) {
                throw new SQLException("Args not match");
            }

            Specification<T> specification = new Specification<T>() {
                @Override
                public Predicate getPredicate(TableInfo<T, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                    Predicate predicate = null;

                    for (int i = 0; i < columns.length; i++) {
                        Object arg = args[i];
                        ColumnType columnType = tableInfo.getColumnType(columns[i]);
                        if (predicate == null) {
                            predicate = cb.eq(columnType, arg);
                        } else {
                            predicate = cb.and(predicate, cb.eq(columnType, arg));
                        }
                    }

                    if (predicate == null) {
                        throw new SQLException("Args not match");
                    }

                    return predicate;
                }
            };


            return specification;


        } else {

            throw new SQLException("Format of the method is not correct!");
        }


    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        try {

            Object result = method.invoke(this, args);

            return result;
        } catch (Exception e) {

            //Try to match queryByXXXAndXXX queryByXXXOrXXX
            Specification<T> specification = specificationByAnd(method, args);

            if (specification == null) {
                specification = specificationByOr(method, args);
            }

            if (specification == null) {
                throw new Exception("Query error in " + method.getName());
            }

            Class<?> returnType = method.getReturnType();

            if (returnType == List.class) {
                return query(specification);
            } else {
                return queryOne(specification);
            }

        }


    }
}
