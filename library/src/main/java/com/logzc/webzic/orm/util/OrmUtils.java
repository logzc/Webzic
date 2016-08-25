package com.logzc.webzic.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 * Created by lishuang on 2016/8/24.
 */
public class OrmUtils {

    //this is a Util method.
    public static Method findGetter(Field field) {
        Class<?> clazz = field.getDeclaringClass();

        String name = field.getName();

        if (name.length() == 0) {
            return null;
        }

        String getterName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method getterMethod;

        try {
            getterMethod = clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            return null;
        }

        return getterMethod;

    }

    //this is a Util method.
    public static Method findSetter(Field field) {
        Class<?> clazz = field.getDeclaringClass();

        String name = field.getName();

        if (name.length() == 0) {
            return null;
        }

        String getterName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method getterMethod;

        try {
            getterMethod = clazz.getMethod(getterName, field.getType());
        } catch (NoSuchMethodException e) {
            return null;
        }

        return getterMethod;

    }

    public static int getSqlType(Class<?> type) throws SQLException{

        int sqlType;
        if (type == int.class || type == Integer.class) {
            sqlType = Types.INTEGER;
        } else if (type == long.class || type == Long.class) {
            sqlType = Types.BIGINT;
        } else if (type == float.class || type == Float.class) {
            sqlType = Types.FLOAT;
        } else if (type == double.class || type == Double.class) {
            sqlType = Types.DOUBLE;
        } else if (type == boolean.class || type == Boolean.class) {
            sqlType = Types.BOOLEAN;
        } else if (type == Date.class) {
            sqlType = Types.TIMESTAMP;
        } else if (type == String.class) {
            sqlType = Types.VARCHAR;
        } else {
            throw new SQLException("Not supported types.");
        }
        return sqlType;
    }
}
