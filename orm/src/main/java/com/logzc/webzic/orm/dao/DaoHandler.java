package com.logzc.webzic.orm.dao;

import com.logzc.common.util.ClassUtil;
import com.logzc.webzic.orm.field.ColumnType;
import com.logzc.webzic.orm.stmt.query.CriteriaBuilder;
import com.logzc.webzic.orm.stmt.query.Predicate;
import com.logzc.webzic.orm.stmt.query.Specification;
import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.table.TableInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lishuang on 2016/10/12.
 */
public class DaoHandler<T, ID> extends BaseDao<T, ID> implements InvocationHandler {


    public static final String GLUE_AND = "And";
    public static final String GLUE_OR = "Or";

    //Methods we can handle.
    public Map<Method,ColumnType[]> methodColumnTypeMap = new HashMap<>();
    public Map<Method,String> methodGlueMap = new HashMap<>();


    public DaoHandler(ConnectionSource connectionSource, Class<T> dataClass, Class<?> repositoryClass) throws SQLException {
        super(connectionSource, dataClass);

        //Check the validation for the repositoryClass.

        //This includes parent methods.
        //Method[] methods = repositoryClass.getMethods();

        //This only includes current class methods.
        Method[] methods = repositoryClass.getDeclaredMethods();

        for (Method method : methods) {
            checkMethod(method);
        }
        System.out.println("Hi");

    }

    //Check the validation of a method name.
    public void checkMethod(Method method) throws SQLException {

        //1.queryByXXXAnd format.
        ColumnType[] columnTypes = columnTypesByGlue(method, GLUE_AND);
        if (columnTypes != null) {
            methodColumnTypeMap.put(method,columnTypes);
            methodGlueMap.put(method,GLUE_AND);
            return;
        }

        //2.queryByXXXOr format
        columnTypes = columnTypesByGlue(method, GLUE_OR);
        if (columnTypes != null) {
            methodColumnTypeMap.put(method,columnTypes);
            methodGlueMap.put(method,GLUE_OR);
            return;
        }

        throw new SQLException("Method " + method.getName() + " is invalid!");
    }

    public ColumnType[] columnTypesByGlue(Method method, String glue) throws SQLException {
        String methodName = method.getName();
        String pattern = "^queryBy(.+)$";


        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(methodName);
        if (m.find()) {
            String clause = m.group(1);
            String[] columns = clause.split(glue);

            Class<?>[] types = method.getParameterTypes();

            if (columns.length != types.length || columns.length == 0) {
                return null;
            }

            ColumnType[] columnTypes = new ColumnType[columns.length];


            for (int i = 0; i < columns.length; i++) {

                ColumnType columnType = tableInfo.getColumnType(columns[i]);

                //Check ColumnType and Arguments Type.
                if (ClassUtil.resolvePrimitive(columnType.getType()) == ClassUtil.resolvePrimitive(types[i])) {
                    columnTypes[i] = columnType;
                } else {
                    throw new SQLException("Column Type ( " + columnType.getType() + ") doesn't match Parameter Type ( " + types[i] + " )");
                }

            }

            return columnTypes;

        } else {
            return null;
        }
    }

    public Specification<T> specificationByOr(Method method, Object[] args) {

        ColumnType[] columnTypes=methodColumnTypeMap.get(method);

        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate getPredicate(TableInfo<T, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                Predicate predicate = null;

                for (int i = 0; i < columnTypes.length; i++) {
                    Object arg = args[i];
                    ColumnType columnType = columnTypes[i];
                    if (predicate == null) {
                        predicate = cb.eq(columnType, arg);
                    } else {
                        predicate = cb.or(predicate, cb.eq(columnType, arg));
                    }
                }

                if (predicate == null) {
                    throw new SQLException("Args not match");
                }

                return predicate;
            }
        };


        return specification;
    }


    public Specification<T> specificationByAnd(Method method, Object[] args) throws SQLException {


        ColumnType[] columnTypes=methodColumnTypeMap.get(method);

        Specification<T> specification = new Specification<T>() {
            @Override
            public Predicate getPredicate(TableInfo<T, ?> tableInfo, CriteriaBuilder cb) throws SQLException {

                Predicate predicate = null;

                for (int i = 0; i < columnTypes.length; i++) {
                    Object arg = args[i];
                    ColumnType columnType = columnTypes[i];
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

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        String glue=methodGlueMap.get(method);
        if(glue!=null){
            Specification<T> specification;
            if(GLUE_AND.equals(glue)){
                specification = specificationByAnd(method, args);
            }else if(GLUE_OR.equals(glue)){
                specification = specificationByOr(method, args);
            }else{
                throw new SQLException("Cannot handle method "+method.getName());
            }

            Class<?> returnType = method.getReturnType();

            if (returnType == List.class) {
                return query(specification);
            } else {
                return queryOne(specification);
            }
        }else{

            Object result = method.invoke(this, args);

            return result;

        }

    }
}
