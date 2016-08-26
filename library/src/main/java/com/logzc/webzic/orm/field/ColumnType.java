package com.logzc.webzic.orm.field;

import com.logzc.webzic.orm.util.OrmUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by lishuang on 2016/8/23.
 */
public class ColumnType {


    private final Field field;
    private String name;

    private ColumnStrategy columnStrategy;

    //get and set method.
    private final Method getter;
    private final Method setter;

    private boolean isId = false;

    private ColumnType(Field field, ColumnStrategy columnStrategy) {

        this.field = field;
        this.columnStrategy = columnStrategy;

        //get name from @Column.
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            if (column.name().length() > 0) {
                this.name = column.name();
            } else {
                this.name = this.field.getName();
            }
            //check whether it is id.
            this.isId = column.id();

        } else {
            this.name = this.field.getName();
            this.isId = false;
        }

        //find the get and set method.
        getter = OrmUtils.findGetter(this.field);
        setter = OrmUtils.findSetter(this.field);
    }


    public static ColumnType create(Field field, ColumnStrategy columnStrategy) {


        if (field.isAnnotationPresent(Transient.class)) {
            return null;
        } else {
            return new ColumnType(field, columnStrategy);
        }

    }


    public Class<?> getType() {
        return this.field.getType();
    }


    public String getName() {

        if (columnStrategy == ColumnStrategy.CAMEL_TO_UNDERSCORE) {
            return OrmUtils.convertCamelToUnderscore(name);
        } else {
            return name;
        }
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
