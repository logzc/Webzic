package com.logzc.webzic.orm.field;

import com.logzc.webzic.orm.support.ConnectionSource;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by lishuang on 2016/8/23.
 */
public class ColumnType {


    private final ConnectionSource connectionSource;
    private final String tableName;
    private final Field field;
    private final Class<?> containClass;
    private String name;

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


    //TODO:Finish the sql type.
    public int getSqlType() {

        return Types.VARCHAR;

    }

    public String getName() {
        return name;
    }

    //Assign value to the class.
    public <T> void assign(T entity, Object val) throws SQLException {
        try {
            field.set(entity, val);
        } catch (IllegalAccessException e) {
            throw new SQLException("Assign value exception.");
        }
    }
}
