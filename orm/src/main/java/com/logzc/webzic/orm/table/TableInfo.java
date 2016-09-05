package com.logzc.webzic.orm.table;

import com.logzc.webzic.orm.field.ColumnStrategy;
import com.logzc.webzic.orm.field.ColumnType;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuang on 2016/8/23.
 */
public class TableInfo<T, ID> {


    private final Class<T> tableClass;
    private String tableName;
    private ColumnStrategy columnStrategy;
    protected ColumnType[] columnTypes;
    protected String[] columnNames;
    private ColumnType idColumnType;

    public TableInfo(Class<T> tableClass) throws SQLException {

        this.tableClass = tableClass;

        initBasicInfo();

        initColumnInfo();

    }


    //get the table name from Class.
    public void initBasicInfo() {

        Table table = this.tableClass.getAnnotation(Table.class);

        if (table != null) {

            this.columnStrategy = table.columnStrategy();

            if (table.name().length() > 0) {
                this.tableName = table.name();
            } else {
                this.tableName = this.tableClass.getSimpleName().toLowerCase();
            }

        } else {
            this.columnStrategy = ColumnStrategy.CAMEL_TO_UNDERSCORE;

            this.tableName = this.tableClass.getSimpleName().toLowerCase();
        }

    }


    private void initColumnInfo() throws SQLException {

        List<ColumnType> columnTypeList = new ArrayList<>();

        //not only self but also super father.
        for (Class<?> classWalk = this.tableClass; classWalk != null; classWalk = classWalk.getSuperclass()) {

            for (Field field : classWalk.getDeclaredFields()) {

                ColumnType columnType = ColumnType.create(field, this.columnStrategy);

                if (columnType != null) {
                    columnTypeList.add(columnType);
                }

            }
        }


        if (columnTypeList.isEmpty()) {
            throw new IllegalArgumentException("No columns configured.");
        }

        this.columnTypes = columnTypeList.toArray(new ColumnType[columnTypeList.size()]);


        //find the id column.
        for (ColumnType columnType : this.columnTypes) {
            if (columnType.isId()) {
                if (idColumnType == null) {
                    idColumnType = columnType;
                } else {
                    throw new SQLException("multi id for column defined.");
                }
            }
        }


        //init the columnNames.
        columnNames = new String[this.columnTypes.length];
        for (int i = 0; i < this.columnTypes.length; i++) {
            ColumnType columnType = this.columnTypes[i];
            columnNames[i] = columnType.getName();
        }

    }


    public Object[] getColumnValues(T entity) throws SQLException {
        int length = this.columnTypes.length;
        Object[] objects = new Object[length];
        for (int i = 0; i < length; i++) {
            ColumnType columnType = this.columnTypes[i];


            objects[i] = columnType.getValue(entity);
        }
        return objects;
    }

    public Class<T> getTableClass() {
        return tableClass;
    }

    public ColumnType getIdColumnType() {
        return idColumnType;
    }

    public String getTableName() {
        return tableName;
    }

    public ColumnType[] getColumnTypes() {
        return columnTypes;
    }

    public T createEntity() throws SQLException {
        try {
            return this.tableClass.newInstance();
        } catch (Exception e) {
            throw new SQLException("You must define constructor without parameters.");
        }

    }

    @SuppressWarnings("unchecked")
    public ID getIdValue(T entity) throws SQLException {
        if (idColumnType == null) {
            return null;
        } else {
            return (ID) idColumnType.getValue(entity);
        }
    }


    public ColumnType getColumnType(String name) throws SQLException {

        for (ColumnType columnType : this.columnTypes) {
            if (columnType.getName().equals(name)) {
                return columnType;
            }
        }

        throw new SQLException("No such column in the table.");
    }

    public String[] getColumnNames() {
        return columnNames;
    }
}
