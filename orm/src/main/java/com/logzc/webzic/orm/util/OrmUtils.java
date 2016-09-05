package com.logzc.webzic.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lishuang on 2016/8/24.
 */
public class OrmUtils {

    public static final String DEFAULT_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT="HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd";


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

    public static int getSqlType(Class<?> type) throws SQLException {

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
        } else if (type == Date.class || type == java.sql.Date.class) {
            sqlType = Types.TIMESTAMP;
        } else if (type == String.class) {
            sqlType = Types.VARCHAR;
        } else {
            throw new SQLException("Not supported types.");
        }
        return sqlType;
    }


    public static String convertCamelToUnderscore(String camel) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        if (camel == null) {
            return null;
        } else {
            return camel.replaceAll(regex, replacement).toLowerCase();
        }
    }


    /**
     * By default the first letter of the camel is lowercase.
     */
    public static String convertUnderScoreToCamel(String underscore) {

        return convertUnderScoreToCamel(underscore, false);
    }

    public static String convertUnderScoreToCamel(String underscore, boolean firstLetterUpper) {
        //String s = "W_ACT_010_LOG_DATE";

        String[] atoms = underscore.split("_");

        StringBuilder camel = new StringBuilder();

        for (int i = 0; i < atoms.length; i++) {
            String atom = atoms[i];
            if (atom.length() > 0) {
                if (i == 0 && !firstLetterUpper) {
                    camel.append(Character.toLowerCase(atom.charAt(0)));
                } else {
                    camel.append(Character.toUpperCase(atom.charAt(0)));
                }
            }
            if (atom.length() > 1) {
                camel.append(atom.substring(1).toLowerCase());
            }
        }

        return camel.toString();
    }
    //根据日期，格式化成字符串 最常见格式 yyyy-MM-dd HH:mm:ss
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat dateFm = new SimpleDateFormat(pattern);

        return dateFm.format(date);
    }
    public static String dateFormat(Date date) {
        SimpleDateFormat dateFm = new SimpleDateFormat(DEFAULT_FORMAT);

        return dateFm.format(date);
    }


    //根据字符串，转换成时间
    public static Date toDate(String dateString,String pattern){
        SimpleDateFormat dateFm = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFm.parse( dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;

    }

    public static boolean sameDay(Date date1,Date date2){
        if(date1==null || date2==null){
            return false;
        }
        Calendar calendar1=Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2=Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_MONTH)==calendar2.get(Calendar.DAY_OF_MONTH);

    }

}
