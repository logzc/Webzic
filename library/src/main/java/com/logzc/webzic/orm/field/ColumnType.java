package com.logzc.webzic.orm.field;

import com.logzc.webzic.orm.support.ConnectionSource;
import com.logzc.webzic.orm.util.OrmUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 * Created by lishuang on 2016/8/23.
 */
public class ColumnType {


    private final ConnectionSource connectionSource;
    private final String tableName;
    private final Field field;
    private final Class<?> containClass;
    private String name;

    //get and set method.
    private final Method getter;
    private final Method setter;

    private boolean isId = false;

    public ColumnType(ConnectionSource connectionSource, String tableName, Field field, Class<?> containClass) {
        this.connectionSource = connectionSource;
        this.tableName = tableName;

        this.field = field;
        this.containClass = containClass;


        //get name from @Column.
        Column column = field.getAnnotation(Column.class);
        if (column == null) {
            throw new IllegalArgumentException("@Column is must.");
        }
        if (column.name().length() > 0) {
            this.name = column.name();
        } else {
            this.name = this.field.getName();
        }

        //check whether it is id.
        this.isId = column.id();


        //find the get and set method.
        getter = OrmUtils.findGetter(this.field);
        setter = OrmUtils.findSetter(this.field);
    }

    public static ColumnType create(ConnectionSource connectionSource, String tableName, Field field, Class<?> dataClass) {


        if (field.isAnnotationPresent(Column.class)) {
            return new ColumnType(connectionSource, tableName, field, dataClass);
        } else {
            return null;
        }


    }


    public Class<?> getType() {
        return this.field.getType();
    }


    public int getSqlType() throws SQLException {

        Class<?> type = this.getType();
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

    public String getName() {
        return name;
    }

    //Assign value to the class.
    public <T> void assign(T entity, Object val) throws SQLException {

        try {
            //first use the setter.
            if (setter != null) {
                setter.invoke(entity, val);
            } else {
                field.setAccessible(true);
                field.set(entity, val);
            }
        } catch (Exception e) {
            throw new SQLException("Assign value exception.");
        }
    }


    //insert data to database.
    public <T> Object getValue(T entity) throws SQLException {
        Object val;
        try {
            if (getter != null) {
                val = getter.invoke(entity);
            } else {
                field.setAccessible(true);
                val = field.get(entity);
            }
        } catch (Exception e) {
            throw new SQLException("Assign value exception.");
        }
        return val;
    }

    public boolean isId() {
        return isId;
    }


}
